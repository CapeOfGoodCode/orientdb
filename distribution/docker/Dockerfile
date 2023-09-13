############################################################
# Dockerfile to run an OrientDB (Graph) Container
# for current developed version
############################################################

FROM azul/zulu-openjdk:8-latest

MAINTAINER OrientDB LTD (info@orientdb.com)

RUN mkdir /orientdb

COPY orientdb-community-*.tar.gz /orientdb-community.tar.gz

RUN tar -xvzf orientdb-community.tar.gz -C /orientdb --strip-components=1\
      && rm orientdb-community.tar.gz \
      && rm -rf /orientdb/databases/*

RUN rm -rf /orientdb/databases/*

RUN rm /orientdb/lib/log4j-1.2.16.jar
RUN rm /orientdb/lib/ivy-2.3.0.jar
RUN rm /orientdb/lib/jettison-1.3.3.jar

ENV PATH /orientdb/bin:$PATH

VOLUME ["/orientdb/backup", "/orientdb/databases", "/orientdb/config", "orientdb/log"]

WORKDIR /orientdb

#OrientDb binary
EXPOSE 2424

#OrientDb http
EXPOSE 2480

# Default command start the server
CMD ["server.sh"]

HEALTHCHECK CMD curl --fail http://localhost:2480/ || exit 1