create table ani_place
(
    id           int(10)                             NOT NULL AUTO_INCREMENT comment '장소 고유번호',
    name         varchar(100)                        NOT NULL comment '장소 이름',
    type         enum ('R','I','E')                  NOT NULL comment '장소 타입(R:Regular,I:Irregular,E:Etc)',
    address      varchar(255)                        NULL comment '장소 주소',
    phone_number varchar(150)                        NULL comment '장소 연락처',
    created_date timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL comment '생성일',
    updated_date timestamp DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP comment '업데이트일',
    primary key (id)
) COMMENT '봉사장소' collate = utf8mb4_bin;

create table ani_subway
(
    id           int(10)                             NOT NULL AUTO_INCREMENT comment '지하철역 고유번호',
    city         varchar(50)                         NOT NULL comment '도시명',
    name         varchar(150)                        NOT NULL comment '지하철역 이름',
    created_date timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL comment '생성일',
    primary key (id)
) COMMENT '지하철 정보' collate = utf8mb4_bin;

create table ani_users
(
    id           int(10)            NOT NULL AUTO_INCREMENT comment '회원 고유번호',
    user_code    varchar(50)        NOT NULL comment '회원 코드',
    email        varchar(150)       NOT NULL comment '이메일',
    password     varchar(255)       NOT NULL comment '비밀번호',
    type         enum ('M','A','V') NOT NULL default 'V' comment '회원 타입 (M:Master, A:Admin, V:Volunteer)',
    status       tinyint            NOT NULL default 1 comment '회원 상태(0:Stop, 1:Active)',
    name         varchar(100)       NOT NULL comment '회원명',
    phone_number varchar(100)       NOT NULL comment '전화번호',
    subway_id    int(10)            NULL comment '지하철 고유번호',
    use_car      int(2)             NULL comment '차량 여부',
    created_date timestamp                   DEFAULT CURRENT_TIMESTAMP NOT NULL comment '생성일',
    updated_date timestamp                   DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP comment '업데이트일',
    primary key (id),
    constraint ani_users_fk1 foreign key (subway_id) REFERENCES ani_subway (id)
) COMMENT '회원정보' collate = utf8mb4_bin;

create table ani_volunteer_events
(
    id           int(10)      NOT NULL AUTO_INCREMENT comment '봉사 고유번호',
    place_id     int(10)      NOT NULL comment '장소 고유번호',
    round        int(10)      NULL comment '봉사 차수',
    status       tinyint      NOT NULL default 0 comment '봉사 상태(0:Ready, 1:Done)',
    date         date         NOT NULL comment '봉사 날짜',
    time         varchar(150) NULL comment '봉사 시간',
    start_date   datetime comment '봉사 신청 시작 시간',
    end_date     datetime comment '봉사 신청 종로 시간',
    description  text         NULL comment '봉사 설명',
    number       int(10)      NULL comment '봉사 필요 인원',
    created_by   int(10)      NOT NULL comment '생성한 매니저 고유번호',
    created_date timestamp             DEFAULT CURRENT_TIMESTAMP NOT NULL comment '생성일',
    updated_date timestamp             DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP comment '업데이트일',
    primary key (id),
    constraint ani_volunteer_events_fk1 foreign key (place_id) REFERENCES ani_place (id),
    constraint ani_volunteer_events_fk2 foreign key (created_by) REFERENCES ani_users (id)
) COMMENT '봉사정보' collate = utf8mb4_bin;

create table ani_volunteer_regists
(
    id           int(10) NOT NULL AUTO_INCREMENT comment '봉사 신청 고유번호',
    events_id    int(10) NOT NULL comment '봉사 고유번호',
    users_id     int(10) NOT NULL comment '회원 고유번호',
    status       tinyint NOT NULL default 0 comment '봉사 신청 상태(0:Pending, 1:Approved, 4:Rejected)',
    use_car      tinyint NOT NULL default 0 comment '차량 지원(0:Not Use, 1:Use)',
    memo         text    NULL comment '메모',
    created_date timestamp        DEFAULT CURRENT_TIMESTAMP NOT NULL comment '생성일',
    primary key (id),
    constraint ani_volunteer_regists_fk1 foreign key (events_id) REFERENCES ani_volunteer_events (id),
    constraint ani_volunteer_regists_fk2 foreign key (users_id) REFERENCES ani_users (id)
) COMMENT '봉사신청' collate = utf8mb4_bin;


create table ani_invite_user
(
    user_id        int(10) NOT NULL comment '유저 고유번호',
    invite_user_id int(10) NOT NULL comment '초대한 유저 고유번호'
) COMMENT '초대 유저' collate = utf8mb4_bin;