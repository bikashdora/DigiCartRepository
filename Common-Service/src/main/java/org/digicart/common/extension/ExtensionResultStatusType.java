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
package org.digicart.common.extension;


public enum ExtensionResultStatusType {
    HANDLED, // Extension handled the result but leaves it up to the manager to decide what to do next
    HANDLED_CONTINUE, // Extension handled and recommends that the manger continue
    HANDLED_STOP, // Extension handled and recommends that the manger stop
    NOT_HANDLED // Extension did not handle the request
}
