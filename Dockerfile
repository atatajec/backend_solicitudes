services:
  app:
    build: .
    ports:
      - "8086:8086"
    environment:
      - SPRING_PROFILES_ACTIVE=docker
      - SPRING_R2DBC_URL=r2dbc:postgresql://postgres:5432/dbsolicitudes
      - SPRING_R2DBC_USERNAME=solicitud_user
      - SPRING_R2DBC_PASSWORD=solicitud_pass
      - JAVA_OPTS=-Xms256m -Xmx512m
    depends_on:
      postgres:
        condition: service_healthy
    networks:
      - app-network
    restart: unless-stopped

  postgres:
    image: postgres:15-alpine
    environment:
      - POSTGRES_DB=dbsolicitudes
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=root
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
      - ./init.sql:/docker-entrypoint-initdb.d/init.sql
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U postgres -d dbsolicitudes"]
      interval: 10s
      timeout: 5s
      retries: 5
    networks:
      - app-network
    restart: unless-stopped

volumes:
  postgres_data:

networks:
  app-network:
    driver: bridge