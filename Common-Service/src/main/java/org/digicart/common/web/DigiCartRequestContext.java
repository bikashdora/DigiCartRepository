/*
 * #%L
 * DigiCartCommerce Common Libraries
 * %%
 * Copyright (C) 2009 - 2013 DigiCart Commerce
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package org.digicart.common.web;


import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.digicart.common.classloader.release.ThreadLocalManager;
import org.digicart.common.currency.domain.DigiCartCurrency;
import org.digicart.common.dto.RequestDTO;
import org.digicart.common.locale.domain.Locale;
import org.digicart.common.site.domain.Catalog;
import org.digicart.common.site.domain.Site;
import org.digicart.common.site.domain.Theme;
import org.springframework.context.MessageSource;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

/**
 * Convenient holder class for various objects to be automatically available on thread local without invoking the various
 * services yourself
 * 
 * @see {@link DigiCartRequestProcessor}
 */
public class DigiCartRequestContext {
    
    protected static final Log LOG = LogFactory.getLog(DigiCartRequestContext.class);
    
    private static final ThreadLocal<DigiCartRequestContext> DIGICART_REQUEST_CONTEXT = ThreadLocalManager.createThreadLocal(DigiCartRequestContext.class);
    
    public static DigiCartRequestContext getDigiCartRequestContext() {
        return DIGICART_REQUEST_CONTEXT.get();
    }
    
    public static void setDigiCartRequestContext(DigiCartRequestContext DigiCartRequestContext) {
    	DIGICART_REQUEST_CONTEXT.set(DigiCartRequestContext);
    }

    public static boolean hasLocale(){
        if (getDigiCartRequestContext() != null) {
            if(getDigiCartRequestContext().getLocale() != null){
                return true;
            }
        }
        return false;
    }
    
  

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected WebRequest webRequest;
 
    protected Locale locale;
    protected TimeZone timeZone;
    protected DigiCartCurrency digicarCurrency;
    protected Site site;
    protected Theme theme;
    protected java.util.Locale javaLocale;
    protected Currency javaCurrency;
    protected Catalog currentCatalog;
    protected Boolean ignoreSite = false;
    protected Map<String, Object> additionalProperties = new HashMap<String, Object>();
    protected MessageSource messageSource;
    protected RequestDTO requestDTO;
    protected Boolean isAdmin = false;
    protected Long adminUserId;

    protected Boolean internalIgnoreFilters = false;

	private DigiCartCurrency digiCartCurrency;

    /**
     * Gets the current request on the context
     * @return
     */
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * Sets the current request on the context. Note that this also invokes {@link #setWebRequest(WebRequest)} by wrapping
     * <b>request</b> in a {@link ServletWebRequest}.
     * 
     * @param request
     */
    public void setRequest(HttpServletRequest request) {
        this.request = request;
        this.webRequest = new ServletWebRequest(request);
    }

    /**
     * Returns the response for the context
     * 
     * @return
     */
    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * Sets the response on the context
     * 
     * @param response
     */
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    /**
     * Sets the generic request on the context. This is available to be used in non-Servlet environments (like Portlets).
     * Note that if <b>webRequest</b> is an instance of {@link ServletWebRequest} then
     * {@link #setRequest(HttpServletRequest)} will be invoked as well with the native underlying {@link HttpServletRequest}
     * passed as a parameter.
     * <br />
     * <br />
     * Also, if <b>webRequest</b> is an instance of {@link ServletWebRequest} then an attempt is made to set the response
     * (note that this could be null if the ServletWebRequest was not instantiated with both the {@link HttpServletRequest}
     * and {@link HttpServletResponse}
     * @param webRequest
     */
    public void setWebRequest(WebRequest webRequest) {
        this.webRequest = webRequest;
        if (webRequest instanceof ServletWebRequest) {
            this.request = ((ServletWebRequest) webRequest).getRequest();
            setResponse(((ServletWebRequest) webRequest).getResponse());
        }
    }

    /**
     * Returns the generic request for use outside of servlets (like in Portlets). This will be automatically set
     * by invoking {@link #setRequest(HttpServletRequest)}
     * 
     * @return the generic request
     * @see {@link #setWebRequest(WebRequest)}
     */
    public WebRequest getWebRequest() {
        return webRequest;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }



    public Locale getLocale() {
        return locale;
    }

    /**
     * Returns the java.util.Locale constructed from the org.DigiCartcommerce.common.locale.domain.Locale.
     * @return
     */
    public java.util.Locale getJavaLocale() {
        if (this.javaLocale == null) {
            this.javaLocale = convertLocaleToJavaLocale();
        }
        return this.javaLocale;
    }

    /**
     * Returns the java.util.Currency constructed from the org.DigiCartcommerce.common.currency.domain.DigiCartCurrency.
     * If there is no DigiCartCurrency specified this will return the currency based on the JVM locale
     * 
     * @return
     */
    public Currency getJavaCurrency() {
        if (javaCurrency == null) {
            try {
                if (getDigiCartCurrency() != null && getDigiCartCurrency().getCurrencyCode() != null) {
                    javaCurrency = Currency.getInstance(getDigiCartCurrency().getCurrencyCode());
                } else {
                    javaCurrency = Currency.getInstance(getJavaLocale());
                }
            } catch (IllegalArgumentException e) {
                LOG.warn("There was an error processing the configured locale into the java currency. This is likely because the default" +
                		" locale is set to something like 'en' (which is NOT apart of ISO 3166 and does not have a currency" +
                		" associated with it) instead of 'en_US' (which IS apart of ISO 3166 and has a currency associated" +
                		" with it). Because of this, the currency is now set to the default locale of the JVM");
                LOG.warn("To fully resolve this, update the default entry in the BLC_LOCALE table to take into account the" +
                		" country code as well as the language. Alternatively, you could also update the BLC_CURRENCY table" +
                		" to contain a default currency.");
                javaCurrency = Currency.getInstance(java.util.Locale.getDefault());
            }
        }
        return javaCurrency;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
        this.javaLocale = convertLocaleToJavaLocale();
    }

    public String getRequestURIWithoutContext() {
        String requestURIWithoutContext = null;

        if (request.getRequestURI() != null) {
            if (request.getContextPath() != null) {
                requestURIWithoutContext = request.getRequestURI().substring(request.getContextPath().length());
            } else {
                requestURIWithoutContext = request.getRequestURI();
            }

            // Remove JSESSION-ID or other modifiers
            int pos = requestURIWithoutContext.indexOf(";");
            if (pos >= 0) {
                requestURIWithoutContext = requestURIWithoutContext.substring(0,pos);
            }
        }
        
        return requestURIWithoutContext;
        
    }
    
    protected java.util.Locale convertLocaleToJavaLocale() {      
        if (locale == null || locale.getLocaleCode() == null) {
            return java.util.Locale.getDefault();
        } else {
            String localeString = locale.getLocaleCode();
            String[] components = localeString.split("_");
            if (components.length == 1) {
                return new java.util.Locale(components[0]);
            } else if (components.length == 2) {
                return new java.util.Locale(components[0], components[1]);
            } else if (components.length == 3) {
                return new java.util.Locale(components[0], components[1], components[2]);
            }
            return null;            
        }
    }
    
    public boolean isSecure() {
        boolean secure = false;
        if (request != null) {
             secure = ("HTTPS".equalsIgnoreCase(request.getScheme()) || request.isSecure());
        }
        return secure;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public DigiCartCurrency getDigiCartCurrency() {
        return digiCartCurrency;
    }

    public void setDigiCartCurrency(DigiCartCurrency digiCartCurrency) {
        this.digiCartCurrency = digiCartCurrency;
    }

    public Catalog getCurrentCatalog() {
        return currentCatalog;
    }

    public void setCurrentCatalog(Catalog currentCatalog) {
        this.currentCatalog = currentCatalog;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String[]> getRequestParameterMap() {
        return getDigiCartRequestContext().getRequest().getParameterMap();
    }

    public Boolean getIgnoreSite() {
        return ignoreSite;
    }

    public void setIgnoreSite(Boolean ignoreSite) {
        this.ignoreSite = ignoreSite;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public RequestDTO getRequestDTO() {
        return requestDTO;
    }

    public void setRequestDTO(RequestDTO requestDTO) {
        this.requestDTO = requestDTO;
    }

    public Boolean getAdmin() {
        return isAdmin == null ? false : isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Long getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Long adminUserId) {
        this.adminUserId = adminUserId;
    }

    /**
     * Intended for internal use only
     */
    public Boolean getInternalIgnoreFilters() {
        return internalIgnoreFilters;
    }

    /**
     * Intended for internal use only
     */
    public void setInternalIgnoreFilters(Boolean internalIgnoreFilters) {
        this.internalIgnoreFilters = internalIgnoreFilters;
    }
}
