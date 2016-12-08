package com.dark.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

/**
 * A sequence of elements supporting sequential and parallel aggregate
 * operations.<br>
 * 1.Stream是元素的集合，这点让Stream看起来用些类似Iterator.<br>
 * 2.可以支持顺序和并行的对原Stream进行汇聚的操作.
 * 
 * @author Darkidiot
 * @version 1.0
 * @date 2016年10月24日
 */
public class StreamDemo {
	
	/**
	 * 创建stream
	 */
	public static void createStreamDemo() {
		/**
		 * Stream.of
		 * 创建指定元素的stream
		 */
		Stream<Integer> integerStream = Stream.of(1, 2, 3, 5);
		integerStream.forEach(System.out::println);
		
		Stream<String> stringStream = Stream.of("taobao");
		stringStream.forEach(System.out::println);
		
		/**
		 * Stream.generate
		 * 生成一个无限长度的Stream，其元素的生成是通过给定的Supplier（这个接口可以看成一个对象的工厂，每次调用返回一个给定类型的对象）
		 */
		Stream.generate(new Supplier<Double>() {
			@Override
			public Double get() {
				return Math.random();
			}
		}).limit(10).forEach(System.out::println);
		
		Stream.generate(() -> Math.random()).limit(10).forEach(System.out::println);

		Stream.generate(Math::random).limit(10).forEach(System.out::println);

		/**
		 * Stream.iterate
		 * 也是生成无限长度的Stream，其元素的生成是重复对给定的种子值(seed)调用用户指定函数来生成的。其中包含的元素可以认为是：seed，f(seed),f(f(seed))无限循环
		 */
		Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);
		
		/**
		 * 通过Collection子类获取Stream
		 * Collection接口有一个stream方法，所以其所有子类都都可以获取对应的Stream对象。
		 */
		Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10).stream().limit(10).forEach(System.out::println);
	}

	/**
	 *  distinct: 对于Stream中包含的元素进行去重操作（去重逻辑依赖元素的equals方法），新生成的Stream中没有重复的元素
	 */
	public static void distinctDemo(){
		List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
		nums.stream().distinct().forEach(System.out::println);
	}
	
	/**
	 * filter: 对于Stream中包含的元素使用给定的过滤函数进行过滤操作，新生成的Stream只包含符合条件的元素
	 */
	public static void filterDemo(){
		List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
		nums.stream().filter(num -> num != null && num < 5).forEach(System.out::println);
		
	}
	
	/**
	 * map:对于Stream中包含的元素使用给定的转换函数进行转换操作，新生成的Stream只包含转换生成的元素。这个方法有三个对于原始类型的变种方法，分别是：mapToInt，mapToLong和mapToDouble。
	 * 这三个方法也比较好理解，比如mapToInt就是把原始Stream转换成一个新的Stream，这个新生成的Stream中的元素都是int类型。之所以会有这样三个变种方法，可以免除自动装箱/拆箱的额外消耗
	 */
	public static void mapDemo(){
		List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
		nums.stream().map(num -> String.valueOf(num)).forEach(System.out::println);;
	}
	
	/**
	 *  flatMap：和map类似，不同的是其每个元素转换得到的是Stream对象，会把子Stream中的元素压缩到父集合中
	 */
	public static void flatMapDemo(){
		List<Integer> nums = Lists.newArrayList(1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		nums.stream().flatMap(num -> Lists.newArrayList(num,num+1).stream()).forEach(System.out::println);
	}
	
	/**
	 * peek采用懒操作：在调用
	 *  peek: 生成一个包含原Stream的所有元素的新Stream，同时会提供一个消费函数（Consumer实例），新Stream每个元素被消费的时候都会执行给定的消费函数
	 */
	public static void peekDemo(){
		Stream.of("one", "two", "three", "four").peek(e -> System.out.println(e)); //不会有任何输出
		
		Stream.of("one", "two", "three", "four").peek(e -> System.out.println(e)).collect(Collectors.toList());
		
		Stream.of("one", "two", "three", "four")
	    .peek(e -> System.out.println("Peeked value: " + e))
	    .map(String::toUpperCase)
	    .peek(e -> System.out.println("Mapped value: " + e))
	    .collect(Collectors.toList());
	}
	
	/**
	 * limit: 对一个Stream进行截断操作，获取其前N个元素，如果原Stream中包含的元素个数小于N，那就获取其所有的元素
	 */
	public static void limitDemo(){
		List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
		nums.stream().limit(7).forEach(System.out::println);
	}
	
	/**
	 * skip: 返回一个丢弃原Stream的前N个元素后剩下元素组成的新Stream，如果原Stream中包含的元素个数小于N，那么返回空Stream
	 */
	public static void skipDemo(){
		List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
		nums.stream().skip(7).forEach(System.out::println);
	}
	
	
	public static void mixedDemo(){
		List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
		System.out.println("sum is:" + nums.stream().filter(num -> num != null).distinct().mapToInt(num -> num * 2)
				.peek(System.out::println).skip(2).limit(4).sum());
	}
	
	public static void mixedDemoCompare(){
		List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
		System.out.println("sum is:" + nums.stream().filter(num -> num != null).distinct().mapToInt(num -> num * 2)
				.skip(2).peek(System.out::println).limit(4).sum());
	}
	
	/**
	 * 汇聚（Reduce）Stream:
	 * 在对于一个Stream进行多次转换操作，每次都对Stream的每个元素进行转换，而且是执行多次，这样时间复杂度就是一个for循环里把所有操作都做掉的N（转换的次数）倍啊。
	 * 其实不是这样的，转换操作都是lazy的，多个转换操作只会在汇聚操作（见下节）的时候融合起来，一次循环完成。我们可以这样简单的理解，Stream里有个操作函数的集合，
	 * 每次转换操作就是把转换函数放入这个集合中，在汇聚操作的时候循环Stream对应的集合，然后对每个元素执行所有的函数。
	 */
	
	/**
	 * 可变汇聚：把输入的元素们累积到一个可变的容器中，比如Collection或者StringBuilder
	 */
	public static void alterableReduceDemo() {
		List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
		List<Integer> numsWithoutNull = nums.stream().filter(num -> num != null).collect(() -> new ArrayList<Integer>(),
				(list, item) -> list.add(item), (list1, list2) -> list1.addAll(list2));
		System.out.println(numsWithoutNull);
		
		List<Integer> numsWithoutNull2 = nums.stream().filter(num -> num != null).
                collect(Collectors.toList());
		System.out.println(numsWithoutNull2);
		
		
	}
	
	/**
	 * 其他汇聚：除去可变汇聚剩下的，一般都不是通过反复修改某个可变对象，而是通过把前一次的汇聚结果当成下一次的入参，反复如此。比如reduce，count，allMatch
	 */
	public static void otherReduceDemo(){
		List<Integer> ints = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
		System.out.println("ints sum is:" + ints.stream().reduce((sum, item) -> sum + item).get());
		
		System.out.println("ints sum is:" + ints.stream().reduce(0, (sum, item) -> sum + item));
		
		System.out.println("ints sum is:" + ints.stream().count());
		
		System.out.println("ints allMatch is:" + ints.stream().allMatch(item -> item < 100));
		
		System.out.println("ints anyMatch is:" + ints.stream().anyMatch(item -> item < 2));
		
		System.out.println("ints noneMatch is:" + ints.stream().noneMatch(item -> item < 2));
		
		System.out.println("ints findFirst is:" + ints.stream().findFirst().get());
		System.out.println("ints max is:" + ints.stream().max((num1,num2)-> num1 > num2 ? 1 : -1).get());
		System.out.println("ints min is:" + ints.stream().min((num1,num2)-> num1 > num2 ? 1 : -1).get());
	}
	
	/**
	 * main method
	 */
	public static void main(String[] args) {
		System.out.println("====================== create stream Demo ======================");
		createStreamDemo();
		System.out.println("====================== distinct Demo ======================");
		distinctDemo();
		System.out.println("====================== filter Demo ======================");
		filterDemo();
		System.out.println("====================== mixed Demo ======================");
		mixedDemo();
		System.out.println("====================== map Demo ======================");
		mapDemo();
		System.out.println("====================== peek Demo ======================");
		peekDemo();
		System.out.println("====================== limit Demo ======================");
		limitDemo();
		System.out.println("====================== skip Demo ======================");
		skipDemo();
		System.out.println("====================== mixed compare Demo ======================");
		mixedDemoCompare();
		System.out.println("====================== alterable reduce Demo ======================");
		alterableReduceDemo();
		System.out.println("====================== other reduce Demo ======================");
		otherReduceDemo();
	}
}
