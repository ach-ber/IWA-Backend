FROM node:18
WORKDIR /usr/src/app
COPY package*.json ./
RUN npm ci --omit=dev
COPY index.js fake_candidates.json ./
EXPOSE 3000
CMD [ "npm", "start" ]
