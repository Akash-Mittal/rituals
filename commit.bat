@echo off
setlocal

set GITHUB_USERNAME=Akash-Mittal
set GITHUB_TOKEN=%GITHUB_TOKEN%
set GITHUB_REPO=rituals

git pull https://%GITHUB_USERNAME%:%GITHUB_TOKEN%@github.com/%GITHUB_USERNAME%/%GITHUB_REPO%.git
git fetch https://%GITHUB_USERNAME%:%GITHUB_TOKEN%@github.com/%GITHUB_USERNAME%/%GITHUB_REPO%.git


git add *.*

git commit -m "Added new files"
git push https://%GITHUB_USERNAME%:%GITHUB_TOKEN%@github.com/%GITHUB_USERNAME%/%GITHUB_REPO%.git

endlocal
