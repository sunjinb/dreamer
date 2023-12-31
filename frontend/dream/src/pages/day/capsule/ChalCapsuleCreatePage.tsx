

//<ChalCapsuleCreate></ChalCapsuleCreate>

// 리액트
import React from "react";

// 컴포넌트
import NavTitleBar from "components/common/NavTitleBar";
import ChalCapsuleCreate from "components/day/capsule/ChalCapsuleCreate";

// 스타일

const ChalCapsuleCreatePage = () => {

  return (
    <>
    <NavTitleBar>타임 캡슐 담기</NavTitleBar>
    <ChalCapsuleCreate />
    </>
  )
}

export default ChalCapsuleCreatePage