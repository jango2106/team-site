NODE_IMAGE="node:14.15.1-alpine3.12"
DOCKER_COMPOSE_PREFIX=teamsite
DOCKER_COMPOSE_NETWORK=${DOCKER_COMPOSE_PREFIX}_network
DOCKER_BUILD_IMAGE_TAG=${DOCKER_COMPOSE_PREFIX}image
DOCKER_BUILD_IMAGE_TAG_RELEASE=${DOCKER_BUILD_IMAGE_TAG}_release
OPTIONAL_APPENDER=2> /dev/null ||:

.PHONY:clean run build-dev build-release deep-clean help

help: ## Display make help
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m\033[0m\n"} /^[$$()% 0-9a-zA-Z_-]+:.*?##/ { printf "  \033[36m%-25s\033[0m %s\n", $$1, $$2 } /^##@/ { printf  "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)

run: control/yarn-install yarn.lock compose ## Start local running fully mocked instance in docker env
	docker run --rm -it \
		--network ${DOCKER_COMPOSE_NETWORK} \
		-w $(PWD) \
		-v $(PWD):$(PWD) \
		-p 8080:8080 \
		${NODE_IMAGE} \
		yarn start

control/yarn-install: control/control control/npm-intall-yarn package.json
	docker run --rm -it -w $(PWD) -v $(PWD):$(PWD) ${NODE_IMAGE} yarn install
	touch "$@"

control/npm-intall-yarn: control/control package.json
	docker run --rm -it -w $(PWD) -v $(PWD):$(PWD) ${NODE_IMAGE} npm add yarn
	rm package-lock.json
	touch "$@"

control/control:
	mkdir -p control
	touch "$@"

compose: clean
	-@docker network create ${DOCKER_COMPOSE_NETWORK}
	docker-compose -p ${DOCKER_COMPOSE_PREFIX} build
	docker-compose -p ${DOCKER_COMPOSE_PREFIX} up -d

clean: ## Remove things created from make file run that are less download heavy
	-rm control/yarn-install
	-docker-compose -p ${DOCKER_COMPOSE_PREFIX} down ${OPTIONAL_APPENDER}
	-docker-compose -p ${DOCKER_COMPOSE_PREFIX} ${OPTIONAL_APPENDER}
	-docker network remove ${DOCKER_COMPOSE_NETWORK} ${OPTIONAL_APPENDER}
	-docker image rm ${DOCKER_BUILD_IMAGE_TAG} ${OPTIONAL_APPENDER}
	-docker image rm ${DOCKER_BUILD_IMAGE_TAG_RELEASE} ${OPTIONAL_APPENDER}
	@echo "Clean successful!"

deep-clean: clean ## Remove all things created from this make file run
	rm -rf control
	rm -rf node_modules
	-docker image rm ${NODE_IMAGE} ${OPTIONAL_APPENDER}
	@echo "Deep clean successful!"

build-dev: yarn.lock package.json ## Build and tag a local dev image
	docker build -t ${DOCKER_BUILD_IMAGE_TAG} --build-arg mode="development" .

build-release: yarn.lock package.json ## Build and tag a release image
	docker build -t ${DOCKER_BUILD_IMAGE_TAG_RELEASE} .