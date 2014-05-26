
package org.digicart.common.dto;


import org.springframework.web.context.request.WebRequest;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by bpolster.
 */
public class RequestDTOImpl implements RequestDTO, Serializable {

    private static final long serialVersionUID = 1L;

   
    private String requestURI;    
    private String fullUrlWithQueryString;    
    private Boolean secure;

    public RequestDTOImpl(HttpServletRequest request) {
        requestURI = request.getRequestURI();
        fullUrlWithQueryString = request.getRequestURL().toString();
        secure = ("HTTPS".equalsIgnoreCase(request.getScheme()) || request.isSecure());
    }

    public RequestDTOImpl(WebRequest request) {
        // Page level targeting does not work for WebRequest.
        secure = request.isSecure();
    }

    /**
     * @return  returns the request not including the protocol, domain, or query string
     */
    public String getRequestURI() {
        return requestURI;
    }

    /**
     * @return Returns the URL and parameters.
     */
    public String getFullUrLWithQueryString() {
        return fullUrlWithQueryString;
    }

    /**
     * @return true if this request came in through HTTPS
     */
    public Boolean isSecure() {
        return secure;
    }

    public String getFullUrlWithQueryString() {
        return fullUrlWithQueryString;
    }

    public void setFullUrlWithQueryString(String fullUrlWithQueryString) {
        this.fullUrlWithQueryString = fullUrlWithQueryString;
    }

    public Boolean getSecure() {
        return secure;
    }

    public void setSecure(Boolean secure) {
        this.secure = secure;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

}
