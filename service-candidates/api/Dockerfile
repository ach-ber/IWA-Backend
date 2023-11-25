FROM node:18-alpine
WORKDIR /usr/src/app
COPY package*.json ./
RUN npm ci --omit=dev
COPY index.js fake_candidates.json ./
EXPOSE 8303
CMD [ "npm", "start" ]
