Git global setup
git config --global user.name "Ignacio Sastre Marotta"
git config --global user.email "ignacio.sastre@fing.edu.uy"

Create a new repository
git clone https://gitlab.fing.edu.uy/tprog/tpgr28.git
cd tpgr28
touch README.md
git add README.md
git commit -m "add README"
git push -u origin master

Push an existing folder
cd existing_folder
git init
git remote add origin https://gitlab.fing.edu.uy/tprog/tpgr28.git
git add .
git commit -m "Initial commit"
git push -u origin master

Push an existing Git repository
cd existing_repo
git remote rename origin old-origin
git remote add origin https://gitlab.fing.edu.uy/tprog/tpgr28.git
git push -u origin --all
git push -u origin --tags
