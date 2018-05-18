package com.fun

import monix.eval.Task

import scala.util.{Failure, Success}

object MonixFun2 extends App {

  import monix.execution.Scheduler.Implicits.global

  t("principal thread")

  val task  = Task{1 + 1}

  val cancelableTask = task.runOnComplete{
    result =>
      result match {
        case Success(value) =>
          t("in the task")
          println(value)
        case Failure(error) => println("error: "  + error.getMessage)
      }
  }



  Thread.sleep(1000)



  def t(msg:String) = {
    println(msg + " " +Thread.currentThread.getName())
  }




}
