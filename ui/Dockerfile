# ARGS:
#   mode - default is "production"

# Build node distro
FROM node:12.18.3-alpine3.12 as build-stage
ARG MODE="production"

WORKDIR /app
COPY package*.json ./
COPY yarn.lock ./
RUN yarn install

COPY ./ .
RUN yarn build -- --mode ${MODE}

# Build deployable nginx
FROM nginx:1.18.0 as production-stage
RUN mkdir /app
COPY --from=build-stage /app/dist /app
COPY nginx.conf /etc/nginx/nginx.conf
