#!/bin/bash

RELEASE=${1:-11}

curl -s 'https://api.github.com/repos/PerfCake/PerfCake/issues?milestone='$RELEASE'&state=all&sort=created&direction=asc&labels=bug' | grep '\("url".*issues\)\|\("title"\)' | grep -v Release | sed -e 's/.*url": "\(.*\)",/ * \1/g' | sed -e 's/.*title": "\(.*\)",/\1/g' > release-$RELEASE-bugs
curl -s 'https://api.github.com/repos/PerfCake/PerfCake/issues?milestone='$RELEASE'&state=all&sort=created&direction=asc&labels=enhancement' | grep '\("url".*issues\)\|\("title"\)' | grep -v Release | sed -e 's/.*url": "\(.*\)",/ * \1/g' | sed -e 's/.*title": "\(.*\)",/\1/g' > release-$RELEASE-enhancement