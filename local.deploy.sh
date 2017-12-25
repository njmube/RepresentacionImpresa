VERSION=0.0.2
IMAGEN=kster/impresion
NAME=imprimir
docker service rm $NAME  || true
mvn package
docker rmi $IMAGEN:$VERSION || true
docker build -t $IMAGEN:$VERSION -f Dockerfile.dev .

docker service create \
        --name $NAME \
        --network appnet \
        --restart-condition any \
        --replicas=1  \
        --restart-delay 5s \
        --update-delay 10s \
        --update-parallelism 1 \
        -p 57782:57782  \
        $IMAGEN:$VERSION

#EOF
