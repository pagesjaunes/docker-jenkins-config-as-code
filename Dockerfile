FROM jenkins/jenkins:2.60.3

# define env variables
ENV JENKINS_REF="/usr/share/jenkins/ref"

# define JVM options
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false \
              -Duser.timezone=Europe/Paris

USER jenkins

# install jenkins plugins
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

# copy jenkins shared configuration
COPY jenkins_config/shared $JENKINS_REF
