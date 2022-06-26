package 신고결과받기

object Solution {
  def solution(id_list: Vector[String], report: Vector[String], k: Int): Vector[Int] = {
    val reported2Reportings: collection.mutable.Map[String, Set[String]] = collection.mutable.Map()
    val reporting2Reporteds: collection.mutable.Map[String, Set[String]] = collection.mutable.Map()

    // id 맵에 등록
    for (id: String <- id_list) {
      reported2Reportings += (id -> Set())
      reporting2Reporteds += (id -> Set())
    }

    // 신고 기록
    for (reportInfo: String <- report) {
      val A2B: Array[String] = reportInfo.split(" ")
      val reporting: String = A2B(0)
      val reported: String = A2B(1)
      // println(reporting2Reported.mkString("->"))
      reported2Reportings(reported) += reporting
      reporting2Reporteds(reporting) += reported
    }
    // println(reported2Reportings)

    // 정지된 유저들
    val blockedUsers: collection.mutable.Set[String] = collection.mutable.Set()
    for (id: String <- id_list) {
      if (reported2Reportings(id).size >= k) {
        blockedUsers += id
      }
    }

    // answer
    var answer: Vector[Int] = Vector()
    for (id <- id_list) {
      var cnt: Int = 0
      for (reporting <- reporting2Reporteds(id)) {
        if (blockedUsers.contains(reporting)) {
          cnt += 1
        }
      }
      answer = answer :+ cnt
    }
    answer
  }

  def main(args: Array[String]): Unit = {
    println(
      solution(
        id_list = Vector("muzi", "frodo", "apeach", "neo"),
        report = Vector("muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"),
        k = 2)
    )
  }
}
