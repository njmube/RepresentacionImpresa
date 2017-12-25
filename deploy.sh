VERSION=0.0.3
IMAGEN=kster/impresion
NAME=imprimir
mvn package
docker build -t $IMAGEN:$VERSION -f Dockerfile.dev .
docker push $IMAGEN:$VERSION

ssh -o "StrictHostKeyChecking no" deploy@18.220.61.105 << EOF
docker pull $IMAGEN:$VERSION
docker service update \
  --image $IMAGEN:$VERSION \
    $NAME || true

docker service create \
        --name $NAME \
        --network appnet \
        --restart-condition any \
        --replicas=1  \
        --restart-delay 5s \
        --update-delay 10s \
        --update-parallelism 1 \
        --constraint 'node.role == worker' \
        $IMAGEN:$VERSION || true

EOF
