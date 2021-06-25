import com.learn.interview.controller.UserController;
import com.learn.interview.service.UserService;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ClassName spring
 * @Description 描述
 * @Date 2021/3/29 5:27 下午
 * @Author lin
 * @Version 1.0
 */
public class MyTest {

	@Test
	public void  test() throws Exception {
		UserController userController = new UserController();
		Class<? extends UserController> clazz = userController.getClass();
		//创建对象
		UserService userService = new UserService();
		Field serviceField = clazz.getDeclaredField("userService");
		serviceField.setAccessible(true);
		// 只有通过方法才能够设置具体的属性值
		String name = serviceField.getName();
       //拼接方法的名称
		name = name.substring(0,1).toUpperCase() + name.substring(1, name.length());
		String setMethodName = "set" + name;
		// 通过方法注入属性的对象
		Method method = clazz.getMethod(setMethodName, UserService.class);
		//反射
		method.invoke(userController, userService);
		System.out.println(userController.getUserService());

	}
}
