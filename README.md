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
