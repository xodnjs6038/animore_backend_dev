create table ani_users (
    id int(10) NOT NULL AUTO_INCREMENT comment '회원 고유번호',
    user_code varchar(50) NOT NULL comment '회원 코드',
    email varchar(150) NOT NULL comment '이메일',
    password varchar(255) NOT NULL comment '비밀번호',
    type enum('M','A','V') NOT NULL default 'V' comment '회원 타입 (M:Master, A:Admin, V:Volunteer)',
    status tinyint NOT NULL default 1 comment '회원 상태(0:Stop, 1:Active)',
    name varchar(100) NOT NULL comment '회원명',
    phone_number varchar(100) NOT NULL comment '전화번호',
    subway_id int(10) NULL comment '지하철 고유번호',
    use_car int(2) NULL comment '차량 여부',
    created_date timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL comment '생성일',
    updated_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL comment '업데이트일',
    primary key (id)
) COMMENT '회원정보' collate = utf8mb4_bin;

create table ani_volunteer_events (
    id int(10) NOT NULL AUTO_INCREMENT comment '봉사 고유번호',
    place_id int(10) NOT NULL comment '장소 고유번호',
    round int(10) NULL comment '봉사 차수',
    status tinyint NOT NULL default 0 comment '봉사 상태(0:Ready, 1:Done)',
    date date NOT NULL comment '봉사 날짜',
    time varchar(150) NULL comment '봉사 시간',
    start_date datetime comment '봉사 신청 시작 시간',
    end_date datetime comment '봉사 신청 종로 시간',
    description text NULL comment '봉사 설명',
    number int(10) NULL comment '봉사 필요 인원',
    created_by int(10) NOT NULL comment '생성한 매니저 고유번호',
    created_date timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL comment '생성일',
    updated_date timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL comment '업데이트일',
    primary key (id)
) COMMENT '봉사정보' collate = utf8mb4_bin;

create table ani_volunteer_regists (
    id int(10) NOT NULL AUTO_INCREMENT comment '봉사 신청 고유번호',
    events_id int(10) NOT NULL comment '봉사 고유번호',
    users_id int(10) NOT NULL comment '회원 고유번호',
    status tinyint NOT NULL default 0 comment '봉사 신청 상태(0:Pending, 1:Approved, 4:Rejected)',
    use_car tinyint NOT NULL default 0 comment '차량 지원(0:Not Use, 1:Use)',
    memo text NULL comment '메모',
    created_date timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL comment '생성일',
    primary key (id)
) COMMENT '봉사신청' collate = utf8mb4_bin;