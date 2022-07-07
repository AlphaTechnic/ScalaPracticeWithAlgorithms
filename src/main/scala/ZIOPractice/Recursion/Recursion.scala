//package ZIOPractice.Recursion
//
//import zio._
//
//import java.io.IOException
//
//object Recursion extends ZIOAppDefault{
//  lazy val readIntOrRetry: ZIO[Has[Console], IOException, Int] =
//    readInt
//    .orElse(Console.printLine("Please enter a valid integer")
//      .zipRight(readIntOrRetry))
//
//  def run = readIntOrRetry
//}
