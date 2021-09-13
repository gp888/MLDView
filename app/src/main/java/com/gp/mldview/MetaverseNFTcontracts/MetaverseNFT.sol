pragma solidity ^0.6.0;
pragma experimental ABIEncoderV2;

import { ERC721 } from "./openzeppelin-solidity/contracts/token/ERC721/ERC721.sol";
import { SafeMath } from "./openzeppelin-solidity/contracts/math/SafeMath.sol";


/**
 * @notice - This is the NFT contract for a metaverse
 */
contract MetaverseNFT is ERC721 {
    using SafeMath for uint256;

    uint256 public currentMetaverseId;

    constructor(
        address owner,  /// Initial owner (Seller)
        string memory _nftName, 
        string memory _nftSymbol,
        string memory _tokenURI,   /// [Note]: TokenURI is URL include ipfs hash
        uint metaversePrice
    ) 
        public 
        ERC721(_nftName, _nftSymbol) 
    {
        mint(owner, _tokenURI);
    }

    /** 
     * @dev mint a metaverseNFT
     * @dev tokenURI - URL include ipfs hash
     */
    function mint(address to, string memory tokenURI) public returns (bool) {
        /// Mint a new MetaverseNFT
        uint newMetaverseId = getNextMetaverseId();
        currentMetaverseId++;
        _mint(to, newMetaverseId);
        _setTokenURI(newMetaverseId, tokenURI);
    }


    ///--------------------------------------
    /// Getter methods
    ///--------------------------------------


    ///--------------------------------------
    /// Private methods
    ///--------------------------------------
    function getNextMetaverseId() private view returns (uint nextMetaverseId) {
        return currentMetaverseId.add(1);
    }
    

}
