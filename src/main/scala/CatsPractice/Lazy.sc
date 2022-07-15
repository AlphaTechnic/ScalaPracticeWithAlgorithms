// val은 eager, memoized
val x = {
  println("Computing X")
  math.random
}
// Computing X
// x: Double = 0.828821237871653

x // first access
// res0: Double = 0.828821237871653

x // second access
// res1: Double = 0.828821237871653


// def는 lazy, not memoized
def y = {
  println("Computing Y")
  math.random
}
// y: Double

y // first access
// Computing Y
// res2: Double = 0.8068798927021629

y // second access
// Computing Y
// res3: Double = 0.9741888122553769


// lazy val은 lazy, memoized
lazy val z = {
  println("Computing Z")
  math.random
}
// z: Double = <lazy>

z // first access
// Computing Z
// res4: Double = 0.8103134694961557

z // second access
// res5: Double = 0.8103134694961557
