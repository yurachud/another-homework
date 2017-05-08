import ch.qos.logback.classic.encoder.PatternLayoutEncoder

def USER_HOME = System.getProperty("user.home")

appender("CONSOLE", ConsoleAppender) {
    // will print "hostname is null"
    encoder(PatternLayoutEncoder) {
        pattern = "%level %logger - %msg%n"
    }
}

appender("FILE", FileAppender) {
    // make use of the USER_HOME variable
    file = "${USER_HOME}/Downloads/swaper/app.log"
    encoder(PatternLayoutEncoder) {
        pattern = "%level %logger - %msg%n"
    }
}

root(INFO, ["CONSOLE", "FILE"])