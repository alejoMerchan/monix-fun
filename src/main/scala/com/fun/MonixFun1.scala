package com.fun



import monix.eval.Task

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object MonixFun1 extends App {

  val taskA  = Task{
    debug("starting task A")
    Thread.sleep(1000)
    debug("finished task A")
  }

  val taskB  = Task{
    debug("starting task B")
    Thread.sleep(1000)
    debug("finished task B")
  }


  import monix.execution.Scheduler.Implicits.global


  debug("Starting Main")

  val f1 = taskA.runAsync
  val f2 = taskB.runAsync

  debug("Continuing main")

  Await.result(f1 zip f2, Duration.Inf)


  def debug(msg: String): Unit = {
    val now = java.time.format.DateTimeFormatter.ISO_INSTANT
      .format(java.time.Instant.now)
      .substring(11, 23)
    val thread = Thread.currentThread.getName()
    println(s"$now [$thread] $msg")
  }





}
