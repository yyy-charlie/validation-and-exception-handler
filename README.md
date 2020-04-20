# validation-and-exception-handler
# 参数校验
使用Spring Validation完成参数分组校验
```java
@Data
public class User {
    //校验分组
    public interface Register {}
    public interface Update {}

    @NotNull(message = "用户ID不能为空", groups = Update.class)
    private Long id;

    @NotNull(message = "用户名不能为空",groups = Register.class)
    @Size(max = 20, message = "用户名不能超过20个字符")
    private String userName;

    @NotNull(message = "密码不能为空",groups = Register.class)
    @Size(max = 20, message = "密码不能超过20个字符")
    private String password;

    @NotNull(message = "邮箱不能为空",groups = Register.class)
    @Email(message = "邮箱格式不对",groups = Register.class)
    private String email;
}
```
```java
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @RequestMapping("register")
    public User registerUser(@RequestBody @Validated(User.Register.class) User user) {
        request.setAttribute(Const.RETURN_MESSAGE, "新增用户信息");
        return userService.addUser(user);
    }
}
```
# 自定义异常处理
- 自定义异常可以携带更多的信息
- 通过全局异常处理 + 自定义异常完成异常操作的规范
```json
{
  "code": 412,
  "message": "参数校验失败",
  "data": "邮箱格式不对"
}
```
# 自定义统一响应
将返回前端的数据包装成统一的响应体
- 返回对象
```java
@RequestMapping("register")
public User registerUser(@RequestBody @Validated(User.Register.class) User user) {
    request.setAttribute(Const.RETURN_MESSAGE, "新增用户信息");
    return userService.addUser(user);
}
```
```json
//将返回的User信息放在data中
{
  "code": 200,
  "message": "新增用户信息成功",
  "data": {
    "id": 343,
    "userName": "yyy-charlie",
    "password": "3gdfgdfg",
    "email": "11122255566@qq.com"
  }
}
```
- 返回布尔值
```java
@RequestMapping("update")
public Boolean updateUser(@RequestBody @Validated(User.Update.class) User user) {
    request.setAttribute(Const.RETURN_MESSAGE, "更新用户信息");
    return userService.updateUser(user);
}
```
```json
{
  "code": 200,
  "message": "更新用户信息成功",
  "data": null
}
```
