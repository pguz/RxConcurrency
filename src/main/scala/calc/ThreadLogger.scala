package calc

trait ThreadLogger {
    def	log(msg: String):	Unit =
      println(s"${Thread.currentThread.getName}:	$msg")
    def	warn(s: String)	=	log("WARN: "	+	s)
    def	error(s: String) = log("ERROR:	"	+	s)
}
