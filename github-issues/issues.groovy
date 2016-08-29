import groovy.json.JsonSlurper;

def release = args.length > 0 ? args[0] : 9;

def json = new JsonSlurper().parse(new URL("https://api.github.com/repos/PerfCake/PerfCake/issues?milestone=$release&state=closed&sort=created&direction=asc&labels=enhancement&per_page=1000"));
def enhancementFile = new File("release-$release-enhancements");
enhancementFile.text="";
json.forEach() {issue ->
   if (issue.labels.find() {it -> it.name == "won't fix"} == null) {
      enhancementFile << " * " + issue.html_url + "\n";
      enhancementFile << issue.title + "\n";
   }
}

json = new JsonSlurper().parse(new URL("https://api.github.com/repos/PerfCake/PerfCake/issues?milestone=$release&state=closed&sort=created&direction=asc&labels=bug&per_page=1000"));
def bugFile = new File("release-$release-bugs");

json.forEach() {issue ->
   if (issue.labels.find() {it -> it.name == "won't fix"} == null) {
      bugFile << " * " + issue.html_url + "\n";
      bugFile << issue.title + "\n";
   }
}

