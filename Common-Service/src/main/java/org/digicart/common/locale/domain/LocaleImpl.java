/*
 * #%L
 * BroadleafCommerce Common Libraries
 * %%
 * Copyright (C) 2009 - 2013 Broadleaf Commerce
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
package org.digicart.common.locale.domain;

import org.digicart.common.currency.domain.DigiCartCurrency;
import org.digicart.common.currency.domain.DigiCartCurrencyImpl;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by jfischer
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "DC_LOCALE")
@Cache(usage= CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="blCMSElements")

public class LocaleImpl implements Locale {

    private static final long serialVersionUID = 1L;

    @Id
    @Column (name = "LOCALE_CODE")
   
    protected String localeCode;

    @Column (name = "FRIENDLY_NAME")
    protected String friendlyName;

    @Column (name = "DEFAULT_FLAG")
    protected Boolean defaultFlag = false;

    @ManyToOne(targetEntity = DigiCartCurrencyImpl.class)
    @JoinColumn(name = "CURRENCY_CODE")
    protected DigiCartCurrency defaultCurrency;

    @Column (name = "USE_IN_SEARCH_INDEX")
    protected Boolean useInSearchIndex = false;
    
    @Override
    public String getLocaleCode() {
        return localeCode;
    }

    @Override
    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    @Override
    public String getFriendlyName() {
        return friendlyName;
    }

    @Override
    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    @Override
    public void setDefaultFlag(Boolean defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    @Override
    public Boolean getDefaultFlag() {
        if (defaultFlag == null) {
            return Boolean.FALSE;
        } else {
            return defaultFlag;
        }
    }

    @Override
    public DigiCartCurrency getDefaultCurrency() {
        return defaultCurrency;
    }

    @Override
    public void setDefaultCurrency(DigiCartCurrency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }
    
    @Override
    public Boolean getUseCountryInSearchIndex() {
        return useInSearchIndex == null ? false : useInSearchIndex;
    }

    @Override
    public void setUseCountryInSearchIndex(Boolean useInSearchIndex) {
        this.useInSearchIndex = useInSearchIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Locale)) {
            return false;
        }

        LocaleImpl locale = (LocaleImpl) o;

        if (localeCode != null ? !localeCode.equals(locale.localeCode) : locale.localeCode != null) {
            return false;
        }
        if (friendlyName != null ? !friendlyName.equals(locale.friendlyName) : locale.friendlyName != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = localeCode != null ? localeCode.hashCode() : 0;
        result = 31 * result + (friendlyName != null ? friendlyName.hashCode() : 0);
        return result;
    }
}
