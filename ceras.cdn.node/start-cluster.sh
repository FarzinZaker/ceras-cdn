docker kill $(docker ps -q)

docker container prune -f

docker run -d \
  --name node_000 \
  -p 8000:80 \
  -v /Volumes/Personal/DevDesk/CDN/data/000/config/:/app/config/ \
  -v /Volumes/Personal/DevDesk/CDN/data/000/files/:/app/files/ \
  ceras/ceras.cdn.node:0.1

docker run -d \
  --name node_100 \
  -p 8100:80 \
  -v /Volumes/Personal/DevDesk/CDN/data/100/config/:/app/config/ \
  -v /Volumes/Personal/DevDesk/CDN/data/100/files/:/app/files/ \
  ceras/ceras.cdn.node:0.1

docker run -d \
  --name node_200 \
  -p 8200:80 \
  -v /Volumes/Personal/DevDesk/CDN/data/200/config/:/app/config/ \
  -v /Volumes/Personal/DevDesk/CDN/data/200/files/:/app/files/ \
  ceras/ceras.cdn.node:0.1

docker run -d \
  --name node_110 \
  -p 8110:80 \
  -v /Volumes/Personal/DevDesk/CDN/data/110/config/:/app/config/ \
  -v /Volumes/Personal/DevDesk/CDN/data/110/files/:/app/files/ \
  ceras/ceras.cdn.node:0.1

docker run -d \
  --name node_120 \
  -p 8120:80 \
  -v /Volumes/Personal/DevDesk/CDN/data/120/config/:/app/config/ \
  -v /Volumes/Personal/DevDesk/CDN/data/120/files/:/app/files/ \
  ceras/ceras.cdn.node:0.1

docker run -d \
  --name node_210 \
  -p 8210:80 \
  -v /Volumes/Personal/DevDesk/CDN/data/210/config/:/app/config/ \
  -v /Volumes/Personal/DevDesk/CDN/data/210/files/:/app/files/ \
  ceras/ceras.cdn.node:0.1

docker run -d \
  --name node_220 \
  -p 8220:80 \
  -v /Volumes/Personal/DevDesk/CDN/data/220/config/:/app/config/ \
  -v /Volumes/Personal/DevDesk/CDN/data/220/files/:/app/files/ \
  ceras/ceras.cdn.node:0.1