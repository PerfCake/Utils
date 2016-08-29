import groovy.json.JsonSlurper;

def json;

/*/
def acl="access_token=" + "place_github_api_access_token_here" + "&";
/*/
def acl=""
//*/

if (args.length == 0) {
   println "Usage: groovy issues.groovy <release_id>";
   json = new JsonSlurper().parse(new URL("https://api.github.com/repos/PerfCake/PerfCake/milestones?" + acl + "state=all"));
   json.forEach() {milestone ->
      println(milestone.number + " = \"" + milestone.title + "\"");
   }
   return;
}

def release = args[0];

json = new JsonSlurper().parse(new URL("https://api.github.com/repos/PerfCake/PerfCake/issues?" + acl + "milestone=$release&state=closed&sort=created&direction=asc&labels=enhancement&per_page=1000"));
	println "Features:";
json.forEach() {issue ->
   if (issue.labels.find() {it -> it.name == "won't fix"} == null) {
      println " * " + issue.html_url;
      println issue.title;
   }
}

println "";

json = new JsonSlurper().parse(new URL("https://api.github.com/repos/PerfCake/PerfCake/issues?" + acl + "milestone=$release&state=closed&sort=created&direction=asc&labels=bug&per_page=1000"));
	println "Bug fixes:"
json.forEach() {issue ->
   if (issue.labels.find() {it -> it.name == "won't fix"} == null) {
      println " * " + issue.html_url ;
      println issue.title;
   }
}

