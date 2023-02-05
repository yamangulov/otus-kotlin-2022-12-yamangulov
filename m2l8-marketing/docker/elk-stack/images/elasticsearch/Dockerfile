FROM elasticsearch:7.10.1
WORKDIR /usr/share/elasticsearch
COPY elasticsearch.yml config/
RUN sudo sh -c "echo \"vm.max_map_count=262144\" > /etc/sysctl.d/10-elasticsearch.conf"; sudo sysctl -p
