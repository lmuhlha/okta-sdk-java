/*
 * Copyright 2014 Stormpath, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.okta.sdk.ds;

import com.okta.sdk.authc.credentials.ClientCredentials;
import com.okta.sdk.cache.CacheManager;
import com.okta.sdk.resource.Resource;

/**
 * A {@code DataStore} is the liaison between client SDK components and the raw Okta REST API.  It is responsible
 * for converting SDK objects (Account, Directory, Group instances, etc) into REST HTTP requests, executing those
 * requests, and converting REST HTTP responses back into SDK objects.
 *
 * @since 0.5.0
 */
public interface DataStore {

    /**
     * Instantiates and returns a new instance of the specified Resource type.  The instance is merely instantiated and
     * is not saved/synchronized with the server in any way. <p> This method effectively replaces the {@code new}
     * keyword that would have been used otherwise if the concrete implementation was known (Resource implementation
     * classes are intentionally not exposed to SDK end-users).
     *
     * @param clazz the Resource class to instantiate.
     * @param <T>   the Resource sub-type
     * @return a new instance of the specified Resource.
     */
    <T extends Resource> T instantiate(Class<T> clazz);

    /**
     * Looks up (retrieves) the resource at the specified {@code href} URL and returns the resource as an instance of
     * the specified {@code class}. <p> The {@code Class} argument must represent an interface that is a sub-interface
     * of {@link Resource}.
     *
     * @param href  the resource URL of the resource to retrieve
     * @param clazz the {@link Resource} sub-interface to instantiate
     * @param <T>   type parameter indicating the returned value is a {@link Resource} instance.
     * @return an instance of the specified class based on the data returned from the specified {@code href} URL.
     */
    <T extends Resource> T getResource(String href, Class<T> clazz);

    /**
     * Returns the ClientCredentials used to authenticate HTTPS requests sent to the Okta API server.
     *
     * @return the ClientCredentials used to authenticate HTTPS requests sent to the Okta API server.
     */
    ClientCredentials getClientCredentials();

    /**
     * Returns the CacheManager used to improve data store performance.
     *
     * @return the CacheManager used to improve data store performance.
     */
    CacheManager getCacheManager();

}
