FROM timescale/timescaledb:latest-pg15

# 复制初始化脚本到容器中
COPY ../init-database.sql /docker-entrypoint-initdb.d/

# 设置权限
RUN chmod 644 /docker-entrypoint-initdb.d/finance_data_only.dump

