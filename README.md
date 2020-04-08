## Welcome to GitHub Pages

You can use the [editor on GitHub](https://github.com/Rajesh-VPai/DOEMatrixGen/edit/master/README.md) to maintain and preview the content for your website in Markdown files.

Whenever you commit to this repository, GitHub Pages will run [Jekyll](https://jekyllrb.com/) to rebuild the pages in your site, from the content in your Markdown files.

### Markdown

Markdown is a lightweight and easy-to-use syntax for styling your writing. It includes conventions for

**Bold** and _Italic_ and `Code` text

[Link] (https://github.com/Rajesh-VPai/DOEMatrixGen) and ![Image] (src)
#### About DOEMatrixGen
DOEMatrixGen is a core Java orthogonal Array Matrix Generator.
It can generate Taguchi as well as Fisher Orthogonal Array Matrix. It supports any condition of mixed levels. It contains an example of how to self test itself using Orthogonal Array for Testing. It contains copious documentation on the code. Code does not throw any Java exception.
- DOEMatrixGen allows full degrees of freedom to the User. The User can generate an OA for any number of runs(rows), any factors (columns) ,any permutation of mixed levels. 
- At the core, the DOEMatrixGen uses a simple modulo counter to generate the Full factorial OA. It also has a classical Taguchi algorithm function that generates pseudo algorithmic (but not full factorial nor pseudo random) cell value. The cell value is reffered to as partial OA.
- The DOEMatrixGen flags off if the runs are unique or duplicate.
- It also calculates the optimal run(rows) for the given levels and factors.(ROWSDOErecommended).

- Development and Test Environment: Windows XP and Java 7 & Netbeans 8.2

##### Project Directory Structure
Available in DOEMatrix.zip
- build
- data
- logs
- nbproject
- src

data Directory Structure
- DOEMatrix : For CSV File Output of DOEMatrixGen OA
- DOESelfTestConfig: For DOEMatrixGen Regression Testing  Config Files: Run the function(method):mainRegresssionDOE
- LevelValues:Contains the Level Mapping for UseLevelsFromFile=true

Files in data Directory (Base):DOEinputGeneralDefault

Files in LevelValues Directory:DOEColumninputLevelSignalNormal

Files in DOESelfTestConfig Directory: All Files DOEinputGeneral000 to DOEinputGeneral029 + DOEinputGeneralDefault

###### User Instructions
The User can use DOEMatrixGen to generate his/her full factorial / partial Orthogonal Array ( Taguchi/Fisher).Once the DOE Matrix is generated, the user can proceed with the experiments. This software does not do the analysis. The user can use Microsoft Excel or any other software.

In certain conditions. the 100% Full factorial OA might not be obtained. In such cases, the user has to manually make the appropriate changes. For help and guidance,the user can refer to the level (strength) analysis at the bottom of the screen/output. The user has to infer which cell has to change.
All column strengths should be 100% in case of a full factorial OA.
Fisher OA is Taguchi OA with level=2.


### Jekyll Themes

Your Pages site will use the layout and styles from the Jekyll theme you have selected in your [repository settings](https://github.com/Rajesh-VPai/DOEMatrixGen/settings). The name of this theme is saved in the Jekyll `_config.yml` configuration file.

### Support or Contact

Having trouble with Pages? Check out our [documentation](https://help.github.com/categories/github-pages-basics/) or [contact support](https://github.com/contact) and we’ll help you sort it out.
