#run this using command
# chmod +x backup.sh
# ./backup.sh

#!/bin/bash

# Config
CONTAINER=taskmanager-db
DB_USER=postgres
DB_NAME=task_manager
BACKUP_DIR=./db-backups
DATE=$(date +%Y-%m-%d_%H-%M-%S)

# Create backup folder if it doesn't exist
mkdir -p $BACKUP_DIR

# Create backup
docker exec $CONTAINER pg_dump -U $DB_USER $DB_NAME > $BACKUP_DIR/backup_$DATE.sql

echo "✅ Backup created: $BACKUP_DIR/backup_$DATE.sql"

# Keep only last 7 backups
cd $BACKUP_DIR
ls -t *.sql | tail -n +8 | xargs rm -f
echo "🧹 Old backups cleaned up"