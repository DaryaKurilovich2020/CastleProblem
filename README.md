Имеется план замка, который разделён на комнаты. Условно замок разделен на m × n клеток. План замка задаётся в виде последовательности чисел, характеризующих эти клетки.

Необходимо определить:

число комнат в замке;
площадь наибольшей комнаты;
какую стену в замке следует удалить, чтобы получить комнату наибольшей возможной площади.
Input
В первой строке записаны числа m и n — число клеток в направлении с севера на юг и с запада на восток (1 ≤ m, n ≤ 1000). В последующих m строках содержится информация о клетках, при этом каждая клетка описывается числом p (0 ≤ p ≤ 15). Это число p является суммой следующих чисел:
1, если клетка имеет западную стену;
2 — северную;
4 — восточную;
8 — южную.
Числа в строках разделены одним или несколькими пробелами.

Output
В первой строке выведите число комнат в замке. Во второй — площадь наибольшей комнаты. В третьей — наибольшую возможную площадь комнаты, полученной после удаления одной стены.
Example
in	
3 3
15 11 6
3 14 5
9 10 12
out
2
8
9
