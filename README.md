# animore_backend_dev
Animore app service backend develope version.

## 초기 프로젝트 세팅 순서
1. 깃 저장소 받기
  >> git clone https://github.com/xodnjs6038/animore_backend_dev.git
2. jdk 다운로드 (있으면 무시)
  >> brew install openjdk@17
  >> sudo ln -sfn /opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-17.jdk
  >> echo 'export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"' >> ~/.zshrc
3. 설치된 자바 버전 확인
  >> java -version
4. 개발 세팅 시작

## DEV 개발 세팅 순서
1. Docker 데스크탑 앱 실행
2. 프로젝트 폴더에서 make run 실행 (
