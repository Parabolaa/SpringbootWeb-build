## Description of Key Packages and Classes

- **`pom.xml`**  
  Maven project descriptor: dependencies (Spring Boot, MyBatis, JWT, etc.), build plugins, project coordinates.

- **`anno/Log.java`**  
  Custom annotation for logging or method tracing.

- **`aop/`**  
  - `LogAspect.java` & `TimeAspect.java` – Aspect classes for logging and performance timing.  
  - `MyLog.java` – Custom meta-annotation used to mark methods for AOP.

- **`config/WebConfig.java`**  
  Implements `WebMvcConfigurer` to register the `LoginCheckInterceptor`.

- **`controller/`**  
  REST controllers handling HTTP requests:  
  - `DeptController.java` – `/depts` endpoints  
  - `EmpController.java` – `/emps` endpoints  
  - `LoginController.java` – Authentication endpoints  
  - `UploadController.java` – File upload endpoints

- **`exception/GlobalExceptionHandler.java`**  
  `@ControllerAdvice` for centralized exception handling and uniform error responses.

- **`filter/`**  
  Servlet Filters for low-level HTTP request/response logic:  
  - `AbcFilter` & `DemoFilter` – sample filters  
  - `LoginCheckFilter` – checks JWT or session before allowing access

- **`interceptor/LoginCheckInterceptor.java`**  
  Spring MVC interceptor for login validation at controller level.

- **`mapper/`**  
  MyBatis mapper interfaces:  
  - `DeptMapper.java`, `EmpMapper.java`, `OperateLogMapper.java`  
  Each corresponds to an XML file in `resources/.../mapper`.

- **`pojo/`**  
  Domain model classes (JavaBeans):  
  - `Dept.java`, `Emp.java`, `OperateLog.java`  
  - `PageBean.java` – paging helper  
  - `Result.java` – uniform API response wrapper

- **`service/`**  
  Business logic layer:  
  - Interfaces `DeptService`, `EmpService`  
  - Implementations in `service/impl`

- **`utils/JwtUtils.java`**  
  JWT token generation, parsing, and validation utilities.

- **`vo/DeptShortVO.java`**  
  View-object for API responses (e.g. hiding sensitive fields).

- **`resources/`**  
  - **`application.properties` / `application.yml`** – Spring Boot & MyBatis configuration (datasource, mapper locations, JWT secret, etc.)  
  - **`mapper/*.xml`** – SQL mappings for MyBatis  
  - **`static/`** – Static HTML/CSS/JS
  - **`templates/`** – Server-side view templates (Thymeleaf, etc.), if any


---

This structure incorporates:  
- **MyBatis** for data persistence (mapper + XML)  
- **Controller → Service → DAO** layered architecture  
- **IOC** (`@Service`, `@Component`, auto-wiring)  
- **AOP** (logging, timing aspects)  
- **JWT authentication** (filters, utilities)  
- **File upload**, **global exception handling**, **VO mapping**, **paging**, etc.  
