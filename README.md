Jenkins Configuration-as-Code
===

This demo image shows how to implement configuration-as-code in Jenkins with Docker and [job-dsl-plugin](https://www.cloudbees.com/rule-jenkins-configuration-code).

:exclamation: Warning! This image is not designed for production use.
Jenkins security has been disabled, use it at your own risk.

### Hierarchy

jenkins_config/shared

* init.groovy.d: offer possibility to setup jenkins configuration at launch, you can add as much groovy scripts as you want
    * startup.groovy: this script initialize some jenkins settings and seeds jobs and views
* seeds: jobs, views offer possibility to seed jobs and views automatically following [job dsl api](https://jenkinsci.github.io/job-dsl-plugin/)  

Dockerfile: Dockerfile used to build image

plugins.txt: jenkins plugins installed to the docker image


### Usage

Build image:

```shell
docker build -t pagesjaunes/docker-jenkins-config-as-code .
```

Run image:

```shell
docker run --rm --name jenkinsdev -p 8080:8080 -p 50000:50000 pagesjaunes/docker-jenkins-config-as-code
```
