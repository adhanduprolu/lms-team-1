# Stage 1: Build the React application
FROM node:20 AS build
# Set the working directory in the container
WORKDIR /app
# Copy package.json and package-lock.json to install dependencies
COPY package*.json ./
# Install dependencies
RUN npm install
# Copy the entire application to the container
COPY . .
# Build the React app
RUN npm run build
# Stage 2: Serve the React application using Nginx
FROM nginx:latest
# Copy the built React app from the previous stage to the Nginx server
COPY --from=build /app/build /usr/share/nginx/html
# Expose port 80 (default port for Nginx)
EXPOSE 80
# Start Nginx when the container starts
CMD ["nginx", "-g", "daemon off;"]
