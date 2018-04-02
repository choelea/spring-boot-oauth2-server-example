# Spring Security OAuth Example

为了更方便的区分，需要配置如下hosts：

```
127.0.0.1  auth.server.com  client.node.com  client.springboot.com
```
测试账户：joe.li@gmail.com/123456

- `spring-security-client` - Client Project which has the UI 
- `spring-security-auth-server` - Has the Authorization Server and Resource Server using MySQL DB
- `http://localhost:8082/ui` - REST end point for UI which will take you to the secure URI `http://localhost:8082/secure` after logging into the auth server `http://localhost:8081/auth/login`

## Nodejs 客户端测试
http://client.node.com:3000/auth/signin