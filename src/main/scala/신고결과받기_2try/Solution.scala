package 신고결과받기_2try

object Solution {
  def solution(id_list: Vector[String], report: Vector[String], k: Int): Vector[Int] = {
    val reporting2reported = report
      .groupBy(_.split(" ")(0))
      .map(d => (d._1 -> d._2.map(s => s.split(" ")(1))))
      .map(d => (d._1 -> d._2.toSet))
//    println(reporting2reported)

    val blockedUsers = reporting2reported
      .flatMap(d => d._2)
      .groupBy(identity)
      .filter(_._2.size >= k).keySet
//    println(blockedUsers)

    id_list
      .map(
        id => reporting2reported.getOrElse(id, Set())
          .intersect(blockedUsers)
          .size
      )
  }

  def main(args: Array[String]): Unit = {
    println(solution(
      id_list = Vector("muzi", "frodo", "apeach", "neo"),
      report = Vector("muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"),
      k = 2,
    ))
  }
}
