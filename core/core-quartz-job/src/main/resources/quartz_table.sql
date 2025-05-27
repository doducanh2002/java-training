-- Table to store job details
CREATE TABLE QRTZ_JOB_DETAILS (
                                  SCHED_NAME VARCHAR(120) NOT NULL,
                                  JOB_NAME VARCHAR(200) NOT NULL,
                                  JOB_GROUP VARCHAR(200) NOT NULL,
                                  DESCRIPTION VARCHAR(250) NULL,
                                  JOB_CLASS_NAME VARCHAR(250) NOT NULL,
                                  IS_DURABLE BOOLEAN NOT NULL,
                                  IS_NONCONCURRENT BOOLEAN NOT NULL,
                                  IS_UPDATE_DATA BOOLEAN NOT NULL,
                                  REQUESTS_RECOVERY BOOLEAN NOT NULL,
                                  JOB_DATA BYTEA NULL,
                                  PRIMARY KEY (SCHED_NAME, JOB_NAME, JOB_GROUP)
);

-- Table to store triggers
CREATE TABLE QRTZ_TRIGGERS (
                               SCHED_NAME VARCHAR(120) NOT NULL,
                               TRIGGER_NAME VARCHAR(200) NOT NULL,
                               TRIGGER_GROUP VARCHAR(200) NOT NULL,
                               JOB_NAME VARCHAR(200) NOT NULL,
                               JOB_GROUP VARCHAR(200) NOT NULL,
                               DESCRIPTION VARCHAR(250) NULL,
                               NEXT_FIRE_TIME BIGINT NULL,
                               PREV_FIRE_TIME BIGINT NULL,
                               PRIORITY INTEGER NULL,
                               TRIGGER_STATE VARCHAR(16) NOT NULL,
                               TRIGGER_TYPE VARCHAR(8) NOT NULL,
                               START_TIME BIGINT NOT NULL,
                               END_TIME BIGINT NULL,
                               CALENDAR_NAME VARCHAR(200) NULL,
                               MISFIRE_INSTR SMALLINT NULL,
                               JOB_DATA BYTEA NULL,
                               PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
                               FOREIGN KEY (SCHED_NAME, JOB_NAME, JOB_GROUP)
                                   REFERENCES QRTZ_JOB_DETAILS(SCHED_NAME, JOB_NAME, JOB_GROUP)
);

-- Table to store simple triggers
CREATE TABLE QRTZ_SIMPLE_TRIGGERS (
                                      SCHED_NAME VARCHAR(120) NOT NULL,
                                      TRIGGER_NAME VARCHAR(200) NOT NULL,
                                      TRIGGER_GROUP VARCHAR(200) NOT NULL,
                                      REPEAT_COUNT BIGINT NOT NULL,
                                      REPEAT_INTERVAL BIGINT NOT NULL,
                                      TIMES_TRIGGERED BIGINT NOT NULL,
                                      PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
                                      FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
                                          REFERENCES QRTZ_TRIGGERS(SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

-- Table to store cron triggers
CREATE TABLE QRTZ_CRON_TRIGGERS (
                                    SCHED_NAME VARCHAR(120) NOT NULL,
                                    TRIGGER_NAME VARCHAR(200) NOT NULL,
                                    TRIGGER_GROUP VARCHAR(200) NOT NULL,
                                    CRON_EXPRESSION VARCHAR(120) NOT NULL,
                                    TIME_ZONE_ID VARCHAR(80),
                                    PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
                                    FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
                                        REFERENCES QRTZ_TRIGGERS(SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

-- Table to store blob triggers
CREATE TABLE QRTZ_BLOB_TRIGGERS (
                                    SCHED_NAME VARCHAR(120) NOT NULL,
                                    TRIGGER_NAME VARCHAR(200) NOT NULL,
                                    TRIGGER_GROUP VARCHAR(200) NOT NULL,
                                    BLOB_DATA BYTEA NULL,
                                    PRIMARY KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP),
                                    FOREIGN KEY (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
                                        REFERENCES QRTZ_TRIGGERS(SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP)
);

-- Table to store calendars
CREATE TABLE QRTZ_CALENDARS (
                                SCHED_NAME VARCHAR(120) NOT NULL,
                                CALENDAR_NAME VARCHAR(200) NOT NULL,
                                CALENDAR BYTEA NOT NULL,
                                PRIMARY KEY (SCHED_NAME, CALENDAR_NAME)
);

-- Table to store paused trigger groups
CREATE TABLE QRTZ_PAUSED_TRIGGER_GRPS (
                                          SCHED_NAME VARCHAR(120) NOT NULL,
                                          TRIGGER_GROUP VARCHAR(200) NOT NULL,
                                          PRIMARY KEY (SCHED_NAME, TRIGGER_GROUP)
);

-- Table to store fired triggers
CREATE TABLE QRTZ_FIRED_TRIGGERS (
                                     SCHED_NAME VARCHAR(120) NOT NULL,
                                     ENTRY_ID VARCHAR(95) NOT NULL,
                                     TRIGGER_NAME VARCHAR(200) NOT NULL,
                                     TRIGGER_GROUP VARCHAR(200) NOT NULL,
                                     INSTANCE_NAME VARCHAR(200) NOT NULL,
                                     FIRED_TIME BIGINT NOT NULL,
                                     SCHED_TIME BIGINT NOT NULL,
                                     PRIORITY INTEGER NOT NULL,
                                     STATE VARCHAR(16) NOT NULL,
                                     JOB_NAME VARCHAR(200) NULL,
                                     JOB_GROUP VARCHAR(200) NULL,
                                     IS_NONCONCURRENT BOOLEAN NULL,
                                     REQUESTS_RECOVERY BOOLEAN NULL,
                                     PRIMARY KEY (SCHED_NAME, ENTRY_ID)
);

-- Table to store scheduler state
CREATE TABLE QRTZ_SCHEDULER_STATE (
                                      SCHED_NAME VARCHAR(120) NOT NULL,
                                      INSTANCE_NAME VARCHAR(200) NOT NULL,
                                      LAST_CHECKIN_TIME BIGINT NOT NULL,
                                      CHECKIN_INTERVAL BIGINT NOT NULL,
                                      PRIMARY KEY (SCHED_NAME, INSTANCE_NAME)
);

-- Table to store locks
CREATE TABLE QRTZ_LOCKS (
                            SCHED_NAME VARCHAR(120) NOT NULL,
                            LOCK_NAME VARCHAR(200) NOT NULL,
                            PRIMARY KEY (SCHED_NAME, LOCK_NAME)
);

-- Indexes for triggers
CREATE INDEX IDX_QRTZ_T_J ON QRTZ_TRIGGERS (SCHED_NAME, JOB_NAME, JOB_GROUP);
CREATE INDEX IDX_QRTZ_T_N_G_STATE ON QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP, TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_N_G_NEXT_FIRE ON QRTZ_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP, NEXT_FIRE_TIME);

-- Indexes for fired triggers
CREATE INDEX IDX_QRTZ_FT_TRIG_INST_NAME ON QRTZ_FIRED_TRIGGERS (SCHED_NAME, INSTANCE_NAME);
CREATE INDEX IDX_QRTZ_FT_INST_JOB_REQ_RCVRY ON QRTZ_FIRED_TRIGGERS (SCHED_NAME, REQUESTS_RECOVERY);
CREATE INDEX IDX_QRTZ_FT_J_G ON QRTZ_FIRED_TRIGGERS (SCHED_NAME, JOB_NAME, JOB_GROUP);
CREATE INDEX IDX_QRTZ_FT_T_G ON QRTZ_FIRED_TRIGGERS (SCHED_NAME, TRIGGER_NAME, TRIGGER_GROUP);

-- Indexes for scheduler state
CREATE INDEX IDX_QRTZ_SCHED_INST_NAME ON QRTZ_SCHEDULER_STATE (SCHED_NAME, INSTANCE_NAME);
