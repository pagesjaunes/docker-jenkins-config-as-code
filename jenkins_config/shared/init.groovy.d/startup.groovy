#!/usr/bin/env groovy

import java.util.logging.Logger
import jenkins.model.Jenkins
import hudson.model.*
import hudson.markup.RawHtmlMarkupFormatter
import javaposse.jobdsl.dsl.DslScriptLoader
import javaposse.jobdsl.plugin.JenkinsJobManagement

/**
 * Startup script
 */

Logger.global.info("[Running] startup script")

configureJenkins()

seedJenkinsJobs()

seedJenkinsViews()

Logger.global.info("[Done] startup script")

/**
 * Configure jenkins default settings
 */
private void configureJenkins() {
    // disable jenkins security
    Jenkins.getInstance().disableSecurity()

    // disable security warning
    List<AdministrativeMonitor> p = jenkins.model.Jenkins.instance.getActiveAdministrativeMonitors()
    p.each { x ->
        if (x.getClass().name.contains("SecurityIsOffMonitor")) {
            x.disable(true)
        }
    }

    // disable remoting cli
    jenkins.model.Jenkins.instance.getDescriptor("jenkins.CLI").get().setEnabled(false)

    // set markupformatter
    Jenkins.instance.setMarkupFormatter(new RawHtmlMarkupFormatter(false))

    // set jenkins executors number
    Jenkins.instance.setNumExecutors(6)
}

/**
 * Creates jenkins default views
 */
private void seedJenkinsViews() {
    File seeds = new File('/var/jenkins_home/seeds/views/')
    JenkinsJobManagement jobManagement = new JenkinsJobManagement(System.out, [:], new File('.'))

    new DslScriptLoader(jobManagement).with {
        seeds.eachFileRecurse (groovy.io.FileType.FILES) { file ->
            if (file.name.endsWith('.groovy')) {
                runScript(file.text)
            }
        }
    }
}

/**
 * Creates jenkins default jobs
 */
private void seedJenkinsJobs() {
    File seeds = new File('/var/jenkins_home/seeds/jobs/')
    JenkinsJobManagement jobManagement = new JenkinsJobManagement(System.out, [:], new File('.'))

    new DslScriptLoader(jobManagement).with {
        seeds.eachFileRecurse (groovy.io.FileType.FILES) { file ->
            if (file.name.endsWith('.groovy')) {
                runScript(file.text)
            }
        }
    }
}

