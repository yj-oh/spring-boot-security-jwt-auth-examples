# π spring-boot-security-jwt-auth-examples
**Spring Security & JWTμ μΉν΄μ§κΈ°**

![](.README_images/63e15eb2.png)

- Spring Boot `2.5.6`
- Spring Security
- Spring Data JPA
- Java 11
- Gradle
- H2 Database

## π€Ή μ€ν
```bash
# Clone this repo
git clone https://github.com/yj-oh/spring-boot-security-jwt-auth-examples.git

# Change directory
cd spring-boot-security-jwt-auth-examples

# Start the Server
./gradlew bootRun
```
- Port `8080`

## π μ μ μ λ³΄

#### H2 Database
- http://localhost:8080/h2-console

#### Swagger
- http://localhost:8080/swagger-ui/index.html

## πββοΈ Endpoints
| Endpoint     | μ€λͺ                  |
|--------------|---------------------|
| /auth/signup | νμ κ°μ               |
| /auth/login  | λ‘κ·ΈμΈ                 |
| /samples     | νμ€νΈμ© μν API (μΈμ¦ νμ) |

- μ μμ μΌλ‘ λ‘κ·ΈμΈμ΄ λμμ λ μλμ κ°μ ννμ μλ΅κ°μ λ°μλ³Ό μ μλ€.
```json
{
  "id": 1,
  "email": "test@test.com",
  "username": "λ§μ΄ν¬μμ‘°μ€ν€",
  "tokenType": "Bearer",
  "token": {JWT}
}
```

## π Structure
```text
π spring-boot-security-jwt-auth-examples
|ββ π src
|   `ββ π main
|       |ββ π java
|       |   `ββ com.yjworld.jwt
|       |       |ββ π common                                   # κ³΅ν΅μΌλ‘ μ°μ΄λ νμΌλ€
|       |       |   |ββ π CommonError.java                         # μμΈ νΈλ€λ§νκΈ° μν κ³΅ν΅ λͺ¨λΈ
|       |       |   `ββ π CustomExceptionHandler.java              # μμΈ νΈλ€λ§
|       |       |ββ π config                                   # μ€μ  νμΌλ€
|       |       |   |ββ π jwt
|       |       |   |   |ββ π JwtAuthenticationEntryPoint.java     # μΈμ¦ μ€ν¨ μ μμΈ νΈλ€λ§
|       |       |   |   |ββ π JwtAuthenticationFilter.java         # μΈμ¦ νν°
|       |       |   |   `ββ π JwtUtils.java                        # ν ν° μμ±, parsing, κ²μ¦ λ±μ λ©μλ λͺ¨μ
|       |       |   |ββ π security
|       |       |   |   |ββ π UserDetailsImpl.java                 # UserDetails κ΅¬ν
|       |       |   |   `ββ π UserDetailsServiceImpl.java          # UserDetailsService κ΅¬ν
|       |       |   |ββ π SecurityConfig.java                      # Security μ€μ 
|       |       |   `ββ π SwaggerConfig.java                       # Swagger μ€μ 
|       |       |ββ π domain
|       |       |   |ββ π auth                                 # μΈμ¦ κ΄λ ¨ API, λ‘μ§
|       |       |   |ββ π common                               # API λ λ²¨μμ κ³΅ν΅μΌλ‘ μ°μ΄λ νμΌλ€
|       |       |   |   `ββ π AuditCreatedAndUpdated.java          # μμ±μΌμ, μμ μΌμ κ³΅ν΅ μ»¬λΌ
|       |       |   |ββ π sample                               # νμ€νΈμ© μν API
|       |       |   `ββ π user                                 # User κ΄λ ¨ λ‘μ§
|       |       `ββ π SpringSecurityJwtApplication.java
|       `ββ π resources
|           `ββ π application.yml
`ββ π build.gradle                                             # Gradle μ€μ  νμΌ
```
