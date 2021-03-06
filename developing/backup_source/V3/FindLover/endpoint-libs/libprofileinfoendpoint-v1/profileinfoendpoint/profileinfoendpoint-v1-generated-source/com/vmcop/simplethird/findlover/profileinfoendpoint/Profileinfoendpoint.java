/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2015-03-26 20:30:19 UTC)
 * on 2015-07-01 at 09:18:09 UTC 
 * Modify at your own risk.
 */

package com.vmcop.simplethird.findlover.profileinfoendpoint;

/**
 * Service definition for Profileinfoendpoint (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link ProfileinfoendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Profileinfoendpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.18.0-rc of the profileinfoendpoint library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://findloverapp.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "profileinfoendpoint/v1/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Profileinfoendpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Profileinfoendpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "getProfileInfo".
   *
   * This request holds the parameters needed by the profileinfoendpoint server.  After setting any
   * optional parameters, call the {@link GetProfileInfo#execute()} method to invoke the remote
   * operation.
   *
   * @param id
   * @return the request
   */
  public GetProfileInfo getProfileInfo(java.lang.Long id) throws java.io.IOException {
    GetProfileInfo result = new GetProfileInfo(id);
    initialize(result);
    return result;
  }

  public class GetProfileInfo extends ProfileinfoendpointRequest<com.vmcop.simplethird.findlover.profileinfoendpoint.model.ProfileInfo> {

    private static final String REST_PATH = "profileinfo/{id}";

    /**
     * Create a request for the method "getProfileInfo".
     *
     * This request holds the parameters needed by the the profileinfoendpoint server.  After setting
     * any optional parameters, call the {@link GetProfileInfo#execute()} method to invoke the remote
     * operation. <p> {@link GetProfileInfo#initialize(com.google.api.client.googleapis.services.Abstr
     * actGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected GetProfileInfo(java.lang.Long id) {
      super(Profileinfoendpoint.this, "GET", REST_PATH, null, com.vmcop.simplethird.findlover.profileinfoendpoint.model.ProfileInfo.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetProfileInfo setAlt(java.lang.String alt) {
      return (GetProfileInfo) super.setAlt(alt);
    }

    @Override
    public GetProfileInfo setFields(java.lang.String fields) {
      return (GetProfileInfo) super.setFields(fields);
    }

    @Override
    public GetProfileInfo setKey(java.lang.String key) {
      return (GetProfileInfo) super.setKey(key);
    }

    @Override
    public GetProfileInfo setOauthToken(java.lang.String oauthToken) {
      return (GetProfileInfo) super.setOauthToken(oauthToken);
    }

    @Override
    public GetProfileInfo setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetProfileInfo) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetProfileInfo setQuotaUser(java.lang.String quotaUser) {
      return (GetProfileInfo) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetProfileInfo setUserIp(java.lang.String userIp) {
      return (GetProfileInfo) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public GetProfileInfo setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public GetProfileInfo set(String parameterName, Object value) {
      return (GetProfileInfo) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "insertProfileInfo".
   *
   * This request holds the parameters needed by the profileinfoendpoint server.  After setting any
   * optional parameters, call the {@link InsertProfileInfo#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link com.vmcop.simplethird.findlover.profileinfoendpoint.model.ProfileInfo}
   * @return the request
   */
  public InsertProfileInfo insertProfileInfo(com.vmcop.simplethird.findlover.profileinfoendpoint.model.ProfileInfo content) throws java.io.IOException {
    InsertProfileInfo result = new InsertProfileInfo(content);
    initialize(result);
    return result;
  }

  public class InsertProfileInfo extends ProfileinfoendpointRequest<com.vmcop.simplethird.findlover.profileinfoendpoint.model.ProfileInfo> {

    private static final String REST_PATH = "profileinfo";

    /**
     * Create a request for the method "insertProfileInfo".
     *
     * This request holds the parameters needed by the the profileinfoendpoint server.  After setting
     * any optional parameters, call the {@link InsertProfileInfo#execute()} method to invoke the
     * remote operation. <p> {@link InsertProfileInfo#initialize(com.google.api.client.googleapis.serv
     * ices.AbstractGoogleClientRequest)} must be called to initialize this instance immediately after
     * invoking the constructor. </p>
     *
     * @param content the {@link com.vmcop.simplethird.findlover.profileinfoendpoint.model.ProfileInfo}
     * @since 1.13
     */
    protected InsertProfileInfo(com.vmcop.simplethird.findlover.profileinfoendpoint.model.ProfileInfo content) {
      super(Profileinfoendpoint.this, "POST", REST_PATH, content, com.vmcop.simplethird.findlover.profileinfoendpoint.model.ProfileInfo.class);
    }

    @Override
    public InsertProfileInfo setAlt(java.lang.String alt) {
      return (InsertProfileInfo) super.setAlt(alt);
    }

    @Override
    public InsertProfileInfo setFields(java.lang.String fields) {
      return (InsertProfileInfo) super.setFields(fields);
    }

    @Override
    public InsertProfileInfo setKey(java.lang.String key) {
      return (InsertProfileInfo) super.setKey(key);
    }

    @Override
    public InsertProfileInfo setOauthToken(java.lang.String oauthToken) {
      return (InsertProfileInfo) super.setOauthToken(oauthToken);
    }

    @Override
    public InsertProfileInfo setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (InsertProfileInfo) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public InsertProfileInfo setQuotaUser(java.lang.String quotaUser) {
      return (InsertProfileInfo) super.setQuotaUser(quotaUser);
    }

    @Override
    public InsertProfileInfo setUserIp(java.lang.String userIp) {
      return (InsertProfileInfo) super.setUserIp(userIp);
    }

    @Override
    public InsertProfileInfo set(String parameterName, Object value) {
      return (InsertProfileInfo) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "listProfileInfo".
   *
   * This request holds the parameters needed by the profileinfoendpoint server.  After setting any
   * optional parameters, call the {@link ListProfileInfo#execute()} method to invoke the remote
   * operation.
   *
   * @return the request
   */
  public ListProfileInfo listProfileInfo() throws java.io.IOException {
    ListProfileInfo result = new ListProfileInfo();
    initialize(result);
    return result;
  }

  public class ListProfileInfo extends ProfileinfoendpointRequest<com.vmcop.simplethird.findlover.profileinfoendpoint.model.CollectionResponseProfileInfo> {

    private static final String REST_PATH = "profileinfo";

    /**
     * Create a request for the method "listProfileInfo".
     *
     * This request holds the parameters needed by the the profileinfoendpoint server.  After setting
     * any optional parameters, call the {@link ListProfileInfo#execute()} method to invoke the remote
     * operation. <p> {@link ListProfileInfo#initialize(com.google.api.client.googleapis.services.Abst
     * ractGoogleClientRequest)} must be called to initialize this instance immediately after invoking
     * the constructor. </p>
     *
     * @since 1.13
     */
    protected ListProfileInfo() {
      super(Profileinfoendpoint.this, "GET", REST_PATH, null, com.vmcop.simplethird.findlover.profileinfoendpoint.model.CollectionResponseProfileInfo.class);
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public ListProfileInfo setAlt(java.lang.String alt) {
      return (ListProfileInfo) super.setAlt(alt);
    }

    @Override
    public ListProfileInfo setFields(java.lang.String fields) {
      return (ListProfileInfo) super.setFields(fields);
    }

    @Override
    public ListProfileInfo setKey(java.lang.String key) {
      return (ListProfileInfo) super.setKey(key);
    }

    @Override
    public ListProfileInfo setOauthToken(java.lang.String oauthToken) {
      return (ListProfileInfo) super.setOauthToken(oauthToken);
    }

    @Override
    public ListProfileInfo setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (ListProfileInfo) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public ListProfileInfo setQuotaUser(java.lang.String quotaUser) {
      return (ListProfileInfo) super.setQuotaUser(quotaUser);
    }

    @Override
    public ListProfileInfo setUserIp(java.lang.String userIp) {
      return (ListProfileInfo) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String cursor;

    /**

     */
    public java.lang.String getCursor() {
      return cursor;
    }

    public ListProfileInfo setCursor(java.lang.String cursor) {
      this.cursor = cursor;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.Integer limit;

    /**

     */
    public java.lang.Integer getLimit() {
      return limit;
    }

    public ListProfileInfo setLimit(java.lang.Integer limit) {
      this.limit = limit;
      return this;
    }

    @Override
    public ListProfileInfo set(String parameterName, Object value) {
      return (ListProfileInfo) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "removeProfileInfo".
   *
   * This request holds the parameters needed by the profileinfoendpoint server.  After setting any
   * optional parameters, call the {@link RemoveProfileInfo#execute()} method to invoke the remote
   * operation.
   *
   * @param id
   * @return the request
   */
  public RemoveProfileInfo removeProfileInfo(java.lang.Long id) throws java.io.IOException {
    RemoveProfileInfo result = new RemoveProfileInfo(id);
    initialize(result);
    return result;
  }

  public class RemoveProfileInfo extends ProfileinfoendpointRequest<Void> {

    private static final String REST_PATH = "profileinfo/{id}";

    /**
     * Create a request for the method "removeProfileInfo".
     *
     * This request holds the parameters needed by the the profileinfoendpoint server.  After setting
     * any optional parameters, call the {@link RemoveProfileInfo#execute()} method to invoke the
     * remote operation. <p> {@link RemoveProfileInfo#initialize(com.google.api.client.googleapis.serv
     * ices.AbstractGoogleClientRequest)} must be called to initialize this instance immediately after
     * invoking the constructor. </p>
     *
     * @param id
     * @since 1.13
     */
    protected RemoveProfileInfo(java.lang.Long id) {
      super(Profileinfoendpoint.this, "DELETE", REST_PATH, null, Void.class);
      this.id = com.google.api.client.util.Preconditions.checkNotNull(id, "Required parameter id must be specified.");
    }

    @Override
    public RemoveProfileInfo setAlt(java.lang.String alt) {
      return (RemoveProfileInfo) super.setAlt(alt);
    }

    @Override
    public RemoveProfileInfo setFields(java.lang.String fields) {
      return (RemoveProfileInfo) super.setFields(fields);
    }

    @Override
    public RemoveProfileInfo setKey(java.lang.String key) {
      return (RemoveProfileInfo) super.setKey(key);
    }

    @Override
    public RemoveProfileInfo setOauthToken(java.lang.String oauthToken) {
      return (RemoveProfileInfo) super.setOauthToken(oauthToken);
    }

    @Override
    public RemoveProfileInfo setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (RemoveProfileInfo) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public RemoveProfileInfo setQuotaUser(java.lang.String quotaUser) {
      return (RemoveProfileInfo) super.setQuotaUser(quotaUser);
    }

    @Override
    public RemoveProfileInfo setUserIp(java.lang.String userIp) {
      return (RemoveProfileInfo) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.Long id;

    /**

     */
    public java.lang.Long getId() {
      return id;
    }

    public RemoveProfileInfo setId(java.lang.Long id) {
      this.id = id;
      return this;
    }

    @Override
    public RemoveProfileInfo set(String parameterName, Object value) {
      return (RemoveProfileInfo) super.set(parameterName, value);
    }
  }

  /**
   * Create a request for the method "updateProfileInfo".
   *
   * This request holds the parameters needed by the profileinfoendpoint server.  After setting any
   * optional parameters, call the {@link UpdateProfileInfo#execute()} method to invoke the remote
   * operation.
   *
   * @param content the {@link com.vmcop.simplethird.findlover.profileinfoendpoint.model.ProfileInfo}
   * @return the request
   */
  public UpdateProfileInfo updateProfileInfo(com.vmcop.simplethird.findlover.profileinfoendpoint.model.ProfileInfo content) throws java.io.IOException {
    UpdateProfileInfo result = new UpdateProfileInfo(content);
    initialize(result);
    return result;
  }

  public class UpdateProfileInfo extends ProfileinfoendpointRequest<com.vmcop.simplethird.findlover.profileinfoendpoint.model.ProfileInfo> {

    private static final String REST_PATH = "profileinfo";

    /**
     * Create a request for the method "updateProfileInfo".
     *
     * This request holds the parameters needed by the the profileinfoendpoint server.  After setting
     * any optional parameters, call the {@link UpdateProfileInfo#execute()} method to invoke the
     * remote operation. <p> {@link UpdateProfileInfo#initialize(com.google.api.client.googleapis.serv
     * ices.AbstractGoogleClientRequest)} must be called to initialize this instance immediately after
     * invoking the constructor. </p>
     *
     * @param content the {@link com.vmcop.simplethird.findlover.profileinfoendpoint.model.ProfileInfo}
     * @since 1.13
     */
    protected UpdateProfileInfo(com.vmcop.simplethird.findlover.profileinfoendpoint.model.ProfileInfo content) {
      super(Profileinfoendpoint.this, "PUT", REST_PATH, content, com.vmcop.simplethird.findlover.profileinfoendpoint.model.ProfileInfo.class);
    }

    @Override
    public UpdateProfileInfo setAlt(java.lang.String alt) {
      return (UpdateProfileInfo) super.setAlt(alt);
    }

    @Override
    public UpdateProfileInfo setFields(java.lang.String fields) {
      return (UpdateProfileInfo) super.setFields(fields);
    }

    @Override
    public UpdateProfileInfo setKey(java.lang.String key) {
      return (UpdateProfileInfo) super.setKey(key);
    }

    @Override
    public UpdateProfileInfo setOauthToken(java.lang.String oauthToken) {
      return (UpdateProfileInfo) super.setOauthToken(oauthToken);
    }

    @Override
    public UpdateProfileInfo setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (UpdateProfileInfo) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public UpdateProfileInfo setQuotaUser(java.lang.String quotaUser) {
      return (UpdateProfileInfo) super.setQuotaUser(quotaUser);
    }

    @Override
    public UpdateProfileInfo setUserIp(java.lang.String userIp) {
      return (UpdateProfileInfo) super.setUserIp(userIp);
    }

    @Override
    public UpdateProfileInfo set(String parameterName, Object value) {
      return (UpdateProfileInfo) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Profileinfoendpoint}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Profileinfoendpoint}. */
    @Override
    public Profileinfoendpoint build() {
      return new Profileinfoendpoint(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link ProfileinfoendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setProfileinfoendpointRequestInitializer(
        ProfileinfoendpointRequestInitializer profileinfoendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(profileinfoendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}
