set -x
java -DresourceUrl="http://localhost:9999/storiesWithAC.xml" \
     -Dusername="" \
     -Dpassword="" \
     -DtestClassesDir="/Users/huangliang/code/integration-test-coverage/it-coverage/target/test-classes/" \
     -DtestPattern=".*Story.*StubTest.*" \
     -DreportOutputDir="/Users/huangliang/code/integration-test-coverage/it-coverage/target/" \
     -jar ./it-coverage/target/it-coverage-1.0-SNAPSHOT-jar-with-dependencies.jar