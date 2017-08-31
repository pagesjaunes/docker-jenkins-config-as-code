def defaultColumns = {
        status()
        weather()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
        slaveOrLabel()
        configureProject()
        buildButton()
        disableProject(true)
}


listView('SAMPLE_VIEW') {
    filterBuildQueue()
    filterExecutors()
    jobs {
        regex(/^sample_.*/)
    }
    columns defaultColumns
}

