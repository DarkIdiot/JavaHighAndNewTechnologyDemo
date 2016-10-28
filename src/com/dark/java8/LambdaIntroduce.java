package com.dark.java8;

/**
 *	<h5><center>** Lambda语法详解  **</center></h5>
 * 我们在此抽象一下lambda表达式的一般语法：																									<br>
 * 
 *  (Type1 param1, Type2 param2, ..., TypeN paramN) -> { 																		<br>
 *  statment1; 																													<br>
 *  statment2; 																													<br>
 *  //............. 																											<br>
 *  return statmentM; 																											<br>
 *  }																															<br>
 *  																															<br>
 * 从lambda表达式的一般语法可以看出来，还是挺符合上面给出的非精确版本的定义–“一段带有输入参数的可执行语句块”。													<br>
 * 
 * 上面的lambda表达式语法可以认为是最全的版本，写起来还是稍稍有些繁琐。别着急，下面陆续介绍一下lambda表达式的各种简化版：											<br>
 * 																																<br>	
 * 1. 参数类型省略–绝大多数情况，编译器都可以从上下文环境中推断出lambda表达式的参数类型。这样lambda表达式就变成了：												<br>
 *	(param1,param2, ..., paramN) -> {																							<br>
 *  statment1; 																													<br>
 *  statment2; 																													<br>
 *  //............. 																											<br>
 *  return statmentM; 																											<br>
 *  } 																															<br>
 *  所以我们最开始的例子就变成了（省略了List的创建）：																							<br>
 * 	List<String> lowercaseNames = names.stream().map((name) -> {return name.toLowerCase();}).collect(Collectors.toList()); 		<br>
 *																																<br>	
 * 2.当lambda表达式的参数个数只有一个，可以省略小括号。lambda表达式简写为：																			<br>
 * 
 *  param1 -> { 																												<br>
 *  statment1; 																													<br>
 *  statment2; 																													<br>
 *  //............. 																											<br>
 *  return statmentM;																											<br>
 *  } 																															<br>
 *  
 *  所以最开始的例子再次简化为：																											<br>
 * 
 * List<String> lowercaseNames = names.stream().map(name -> {return name.toLowerCase();}).collect(Collectors.toList()); 		<br>
 * 																																<br>
 * 3.当lambda表达式只包含一条语句时，可以省略大括号、return和语句结尾的分号。lambda表达式简化为：														<br>
 * 
 * param1 -> statment 																											<br>
 * 所以最开始的例子再次简化为：																											<br>
 * List<String> lowercaseNames = names.stream().map(name -> name.toLowerCase()).collect(Collectors.toList()); 					<br>
 * 																																<br>
 * 4.使用Method Reference	和Construct Reference																					<br>
 * 方法引用可以在某些条件成立的情况下，更加简化lambda表达式的声明。方法引用语法格式有以下三种：																<br>
 * <ul>
 * <li>objectName::instanceMethod	==>	System.out::println 等同于 x->System.out.println(x)</li>									<br>
 * <li>ClassName::staticMethod		==>	Math::max 等同于 (x, y)->Math.max(x,y)</li> 												<br>
 * 前两种方式类似，等同于把lambda表达式的参数直接当成instanceMethod|staticMethod的参数来调用。
 * <li>ClassName::instanceMethod	==>	String::toLowerCase等同于x->x.toLowerCase()</li>											<br>
 * 最后一种方式，等同于把lambda表达式的第一个参数当成instanceMethod的目标对象，其他剩余参数当成该方法的参数。
 * </ul>
 * 构造器引用语法如下：ClassName::new，把lambda表达式的参数当成ClassName构造器的参数 。例如BigDecimal::new等同于x->new BigDecimal(x)。					<br>
 * 5. lambda表达式访问外部变量																										<br>
 * lambda表达式其实是快速创建SAM(Single Abstract Method)接口的语法糖，原先的SAM接口都可以访问接口外部变量，lambda表达式肯定也是可以(在java8中还做了一个小小的升级).	<br>
 * lambda表达式的三个重要组成部分：
 * <ul>
 * <li>输入参数</li>
 * <li>可执行语句</li>
 * <li>存放外部变量的空间</li>
 * </ul>
 * lambda表达式访问外部变量有一个非常重要的限制：变量不可变(只是引用不可变，而不是真正的不可变).															<br>	
 * 因为变量被lambda表达式引用，所以编译器会隐式的把其当成final来处理.在java8对这个限制做了优化，可以不用显示使用final修饰，但是编译器隐式当成final来处理。				<br>
 */
public class LambdaIntroduce {

}
