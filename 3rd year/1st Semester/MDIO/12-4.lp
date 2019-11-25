min: 50 yA + 32 yB + 28 yC + 36 yD
    + yA  2 xAa
    + yA  5 xAb
    + yA  xAc
    + yA  2 xAd
    + yA  5 xAe
    + yB  4 xBa
    + yB  4 xBd
    + yB  9 xBc
    + yB  xBd
    + yB  4 xBe
    + yC  xCa
    + yC  8 xCb
    + yC  5 xCc
    + yC  6 xCd
    + yC  2 xCe
    + yD  7 xDa
    + yD  xDb
    + yD  2 xDc
    + yD  2 xDd
    + yD  8 xDe;

xAa + xAb + xAc + xAd + xAe <= 35 * yA;
xBa + xBb + xBc + xBd + xBe <= 28 * yB;
xCa + XCb + xCc + xCd + xCe <= 22 * yC;
xDa + xDb + xDc + xDd + xDe <= 28 * yD;
xAa + xBa + xCa + xDa >= 14;
xAb + xBb + xCb + xDb >= 12;
xAc + xBc + xCc + xDc >= 10;
xAd + xBd + xCd + xDd >= 12;
xAe + xBe + xCe + xDe >= 8;

bin yA;
bin yB;
bin yC;
bin yD;
int xAa, xAb, xAc, xAd, xAe;
int xBa, xBb, xBc, xBd, xBe;
int xCa, xCb, xCc, xCd, xCe;
int xDa, xDb, xDc, xDd, xDe;
