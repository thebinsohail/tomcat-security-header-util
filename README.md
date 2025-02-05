# GlobalResponseHeaderFilter 🔒

The `GlobalResponseHeaderFilter` is a custom Java Servlet filter designed to enforce security and custom response headers in your web application. This filter ensures that specific HTTP response headers are set and validated on every outgoing response, improving security and consistency.

## Key Features ✨
- **Security Header Enforcement:** Automatically checks for specified security headers in outgoing HTTP responses. If a header is missing or incorrect, it will be added or corrected.
- **Customizable Configuration:** Configure header names and their expected values through the filter's initialization parameters in the `web.xml`. No need to modify the code itself.
- **Extensibility:** Add or remove headers easily by adjusting the configuration without changing the codebase.

## How It Works 🔄

1. **Initialization (`init`):**  
   The filter reads initialization parameters from the filter configuration (typically `web.xml` or equivalent). These parameters contain the header names and expected values.

2. **Header Check (`doFilter`):**  
   The filter checks each outgoing HTTP response for the configured headers. If a header is missing or incorrect, it sets the header to the specified value.

3. **Cleanup (`destroy`):**  
   Any necessary cleanup logic can be implemented here. Currently, this filter doesn’t require additional cleanup steps.

### You can start using this utility by adding the Jar file to your Tomcat Server 

## Steps to Follow 
1. Download the Jar File [Download Here](https://github.com/thebinsohail/tomcat-security-header-util/releases/download/1.0/thebinsohail-security-header-util-1.0.jar).
2. Copy the Jar file to the `apache-tomcat > lib > thebinsohail-security-header-util-1.0.jar` directory.
3. Then, you can configure the tomcat by configuring the `web.xml` which is mentioned below:
   

## Example Configuration in `web.xml` ⚙️

You can configure the filter in your `web.xml` like so:

```xml
<filter>
    <filter-name>GlobalResponseHeaderFilter</filter-name>
    <filter-class>org.security.headers.GlobalResponseHeaderFilter</filter-class>
    <init-param>
        <param-name>Strict-Transport-Security</param-name>
        <param-value>max-age=31536000; includeSubDomains</param-value>
    </init-param>
    <init-param>
        <param-name>X-Content-Type-Options</param-name>
        <param-value>nosniff</param-value>
    </init-param>
    <init-param>
        <param-name>X-XSS-Protection</param-name>
        <param-value>1; mode=block</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>GlobalResponseHeaderFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
