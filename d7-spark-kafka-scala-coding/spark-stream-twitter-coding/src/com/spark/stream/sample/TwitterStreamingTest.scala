package com.spark.stream.sample


import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.streaming.twitter._
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.streaming.Minutes

object TwitterStreamingTest {
  
  System.setProperty("twitter4j.oauth.consumerKey", "PjEDphhZ8Igv4IJO0nP6fkGrC")
  System.setProperty("twitter4j.oauth.consumerSecret", "sbfDg8doSQ9nyWhhhr3FJ8A0Jgnv1tKyvGh30Uy2PrqAqY7UOi")
  System.setProperty("twitter4j.oauth.accessToken", "187280067-nvFtJIKbShfcWnI8EcmS7OGTup4kHghlkYBReqIK")
  System.setProperty("twitter4j.oauth.accessTokenSecret", "3Snfc2TAgerzGZf7CMsFTAdOiJLwV7biT4z6q4Pa3qFVA")

  //val filters = Array("narendramodi")
  val filters = Array("Donald Trump")    // generate message with keyword "trump"
  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("akka").setLevel(Level.OFF)
  val checkpointDir="/Users/jianzhang/spark/checkpoint"

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("TwitterAnalysis").setMaster("local[2]")
    
    val ssc = new StreamingContext(conf,Seconds(5))
    
    //ssc.checkpoint(this.getClass.getClassLoader.getResource("")+"/spark/staging")

    ssc.checkpoint(checkpointDir)
    
    val streams = TwitterUtils.createStream(ssc,None,filters)
    
    //val streams = TwitterUtils.createStream(ssc,None)   // no filter
    
    val statuses = streams.map(status=>status.getText())  
  
    
    statuses.window(Seconds(10)).print()   // filter out key words "Trump
    /*
     *  statuses.window(Seconds(10)).print() output no filter
     * -------------------------------------------
      Time: 1501042180000 ms
      -------------------------------------------
      RT xlmgodgax100  #MTVHottest Lady Gaga
      囚人「俺達三人で酒を飲むっていうのも中々珍しい機会だね」
      キング「どれ、ここは一つ誰が一番お酒に強いか試そうじゃないか」
      魔王様「…」キング「あれ？魔王様もう顔赤いけど風邪？え？酔ったんじゃないよｎウワアア頬に魔力具現化の槍がァアア」
      囚人「しかも服脱ぎだしてるし。魔王様変態♡」
      受け取ったリプライ数:0(前日比:0)
      いいねした数:0(前日比:0)
      いいねされた数:0(前日比:0)
      フォローした数:0(前日比:+1)
      フォローされた数:0(前日比:+2)
      https://t.co/9ewQkbCIHP
      @FreddyArtur23 JAJAJA no, es really
      RT @SplatoonSwitch: there he is https://t.co/jhryfre4vX
      Rajin Main Teka-teki Silang, Otak 10 Tahun Lebih Muda ▶ https://t.co/4smILl1TWF
      (إنهم إن يظهروا عليكم يرجموكم أو يعيدوكم في ملتهم ولن تفلحوا إذا أبدا) [الكهف:20] https://t.co/rVKY67e14T
      @bernalacin35 Yazık ya 😂içim acıdı 🙈
      Chabon me estoy muriendo de CALOR, que clase de muerte es esta
      RT @ha3BZj7ldMiIaBs: アカデミーナイトG20170726
      山﨑賢人✖️新田真剣佑 
      超仲良し本音対談③
      お互いの俳優としての魅力 https://t.co/b9b3GuMxB0
      ...
     * 
     */
    /*
     *   --------- statuses.window(Seconds(10)).print() output with filter narendramodi -----------------------
     *  @trueaum @narendramodi @OmprakashSewa @Bhavna22 @Bapumerepyare @ravirathakur688 @bharti_dhiman @dipa_k2 @bapu_sevak… https://t.co/KFnR4SFbjF
        RT @VinodChavdaBJP: PM Shri @narendramodi ji holds review meeting at Ahmedabad on flood situation in different parts of #Gujarat… 
        RT @Natuljain2B: @adgpi #indiansoldiers #Salute  To our brave soldiers #dedicated #kargilwar #prideofnation #ourlove To the… 
        | #GujaratFloods |
        PM @narendramodi announces Rs 500cr for flood-hit #Gujarat after aerial survey;at least 83 killed https://t.co/e9EAtTw8Cm
        @radha__jha @narendramodi @myogiadityanath @hrpkjk @PiyushGoyalOffc @modified_hindu @jha_Mukesh_ @bafalipara… https://t.co/o8uUJfpdJa
        RT @ashokepandit: Great News : #Terrorist #ShabbirShah of #JKLF is arrested. He is one of those who started terrorism in #Kashmir. Thank u…
        @sudarsansand @PMOIndia @narendramodi @arunjaitley @crpfindia @BSF_India @AnupamPkher @virendersehwag @thekiranbedi… https://t.co/cRMDvBAWb2
     */
    /*
     * 
     * --------- statuses.window(Seconds(10)).print() output with filter Trump -----------------------
     * -------------------------------------------
        Time: 1501042550000 ms
        -------------------------------------------
        RT @LouiseMensch: @ericgarland BLONDE SEDITION #FINCEN f**ked #Daughtergate #Ivanka https://t.co/813rE9W9I3
        RT @RepSwalwell: Trump went from "No Russians" to "no collusion" to "so what, everyone does it" - and that's not OK, I told @maddow. https:…
        The Boy Scouts of America distance themselves from President Trump: https://t.co/sygBTt02A9 via @AOL
        RT @Evan_McMullin: We warned that Trump was under Moscow's influence, unfit, and a danger to the Republic. We repeat that warning today and…
        RT @imillhiser: Classic story. You find a man. You think he shares your lifelong passion for disenfranchising black people. But he'… 
        RT @jonfavs: Let's save feeling defeated for when Trump is signing a bill. 
        
        For now, keep fighting. Only option that can lead to killing t…
        RT @funder: This is evidence Trump got money from Russia to build in America. NBD. But maybe everyone should RT like their hair… 
        @MarthaWolkonsky @TedGenoways @sjmorris31 You Trump-Cultists aren't true Americans or patriots. You're disgraces.
        Trump's nominee to head Justice Department's criminal division used to represent major Russian bank https://t.co/Tr2AZonTm2
        RT @ScottAdamsSays: Scott Adams tells you why President Trump is on the verge of total victory https://t.co/gjrWHvyi3m
        ...
        
        17/07/25 21:15:50 INFO WriteAheadLogManager  for Thread: Attempting to clear 0 old log files in file:/home/jzhang/Intellipaat/spark/checkpoint/receivedBlockMetadata older than 1501042535000: 
        -------------------------------------------
        Time: 1501042555000 ms
        -------------------------------------------
        RT @LouiseMensch: @ericgarland BLONDE SEDITION #FINCEN f**ked #Daughtergate #Ivanka https://t.co/813rE9W9I3
        RT @RepSwalwell: Trump went from "No Russians" to "no collusion" to "so what, everyone does it" - and that's not OK, I told @maddow. https:…
        The Boy Scouts of America distance themselves from President Trump: https://t.co/sygBTt02A9 via @AOL
        RT @Evan_McMullin: We warned that Trump was under Moscow's influence, unfit, and a danger to the Republic. We repeat that warning today and…
        RT @imillhiser: Classic story. You find a man. You think he shares your lifelong passion for disenfranchising black people. But he'… 
        RT @jonfavs: Let's save feeling defeated for when Trump is signing a bill. 
        
        For now, keep fighting. Only option that can lead to killing t…
        RT @funder: This is evidence Trump got money from Russia to build in America. NBD. But maybe everyone should RT like their hair… 
        @MarthaWolkonsky @TedGenoways @sjmorris31 You Trump-Cultists aren't true Americans or patriots. You're disgraces.
        Trump's nominee to head Justice Department's criminal division used to represent major Russian bank https://t.co/Tr2AZonTm2
        RT @ScottAdamsSays: Scott Adams tells you why President Trump is on the verge of total victory https://t.co/gjrWHvyi3m
        ...
        
        17/07/25 21:15:55 INFO WriteAheadLogManager  for Thread: Attempting to clear 0 old log files in file:/home/jzhang/Intellipaat/spark/checkpoint/receivedBlockMetadata older than 1501042540000: 
        -------------------------------------------
        Time: 1501042560000 ms
        -------------------------------------------
        RT @mitchellvii: RALLY UPDATE @GingerMcQueen on #Periscope: With @markthepatriot and the official Trump truck! https://t.co/13dl2JLRfo
        RT @PeeSparkle: THIS tweet..suggesting HRC is a mere political rival & NOT a hardcore criminal who deserves to be brought to justic… 
        A self-proclaimed 'lifelong Democrat' took the stage at a Trump rally wearing a 'TRUMP WON' t-shirt… https://t.co/6NzFROZ7qh
        RT @JimKuther: WATCH Trump’s Ohio Crowd ERUPT in a Chant of "CNN SUCKS! CNN SUCKS!" https://t.co/GJXJMbfxhz via @truthfeednews
        Leave it to trump to do a press conference with mannikins in the back🤦🏾‍♂️ https://t.co/D3Dp45KWwE
        RT @SariHorwitz: Sources tell us that AG Sessions will announce in the next day or two a number of leak investigations. https://t.co/Js5DI2…
        RT maddow "RT RepSwalwell: Trump went from "No Russians" to "no collusion" to "so what, everyone does it" - and th… https://t.co/RKkCJfU0WN"
        Via Amy Siskind
        
        "Tonight at his Ohio rally, Trump made up a story to stoke hate and target people whose skin... https://t.co/vPtkcpeygD
        RT @JoyAnnReid: Anyone still believe the Senate would lift a finger if Trump fired Sessions and then fired Bob Mueller?
        RT @barstoolsports: The lady behind Trump at the last press conference had the most bizarre eyebrow game of ALL TIME https://t.co/ifvWt0Eqiw
        ...
     */
    
    def bond(x:String) = if(x.startsWith("#")) x.substring(1) else x 
      
    val words = statuses.flatMap(p=>p.split(" "))
    var countWords = words.map(w=>(w,1)).reduceByKeyAndWindow(_+_, Seconds(10))
    
    
    countWords.foreachRDD{ rdd =>
      val topList = rdd.take(20).sortBy(x=>(x._2,0))
      println("\n Popular topics in last 10 Seconds (%s total) :".format(rdd.count()))
      //topList.foreach{case (count,topic) => println("%s (%s tweetes)\n".format(count,topic))}
      topList.foreach(x=>println(bond(x._1)+(" ("+x._2+" tweetes)")))
    }
    ssc.start()
    ssc.awaitTermination()

  }
}